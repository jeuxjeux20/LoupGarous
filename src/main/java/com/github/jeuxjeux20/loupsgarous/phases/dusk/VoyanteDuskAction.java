package com.github.jeuxjeux20.loupsgarous.phases.dusk;

import com.github.jeuxjeux20.loupsgarous.Check;
import com.github.jeuxjeux20.loupsgarous.LGSoundStuff;
import com.github.jeuxjeux20.loupsgarous.game.LGGameOrchestrator;
import com.github.jeuxjeux20.loupsgarous.game.LGPlayer;
import com.github.jeuxjeux20.loupsgarous.interaction.AbstractPlayerPick;
import com.github.jeuxjeux20.loupsgarous.interaction.Interactable;
import com.github.jeuxjeux20.loupsgarous.interaction.LGInteractableKeys;
import com.github.jeuxjeux20.loupsgarous.interaction.condition.PickConditions;
import com.github.jeuxjeux20.loupsgarous.powers.VoyantePower;
import com.github.jeuxjeux20.loupsgarous.util.OptionalUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.github.jeuxjeux20.loupsgarous.LGChatStuff.VOYANTE_SYMBOL;
import static com.github.jeuxjeux20.loupsgarous.LGChatStuff.importantTip;

public class VoyanteDuskAction extends DuskAction {
    private final VoyanteLookable look;

    public VoyanteDuskAction(LGGameOrchestrator orchestrator) {
        super(orchestrator);

        this.look = Interactable.createBound(VoyanteLookable::new, LGInteractableKeys.LOOK, this);
    }

    @Override
    protected boolean shouldRun() {
        return orchestrator.getPlayers().stream()
                .anyMatch(Check.predicate(look.conditions()::checkPicker));
    }

    @Override
    protected void onDuskStart() {
        orchestrator.getPlayers().stream()
                .filter(Check.predicate(look.conditions()::checkPicker))
                .map(LGPlayer::minecraft)
                .flatMap(OptionalUtils::stream)
                .forEach(this::sendNotification);
    }

    private void sendNotification(Player player) {
        String text = VOYANTE_SYMBOL +
                      "Vous êtes une voyante ! Faites /lglook <joueur> pour voir le rôle de quelqu'un !";
        player.sendMessage(importantTip(text));
        LGSoundStuff.ding(player);
    }

    @Override
    protected String getMessage() {
        return "La voyante va découvrir le rôle de quelqu'un...";
    }

    public VoyanteLookable look() {
        return look;
    }

    private static final class VoyanteLookable extends AbstractPlayerPick {
        private final List<LGPlayer> playersWhoLooked = new ArrayList<>();

        public VoyanteLookable(LGGameOrchestrator orchestrator) {
            super(orchestrator);
        }

        @Override
        public PickConditions<LGPlayer> pickConditions() {
            return conditionsBuilder()
                    .ensurePicker(this::isVoyante, "Vous n'êtes pas voyante !")
                    .ensurePicker(LGPlayer::isAlive, "Vous êtes mort !")
                    .ensurePicker(this::isPowerAvailable, "Vous avez déjà utilisé votre pouvoir.")
                    .ensureTarget(LGPlayer::isAlive, "La cible est déjà morte !")
                    .build();
        }

        @Override
        protected void safePick(LGPlayer picker, LGPlayer target) {
            playersWhoLooked.add(picker);

            picker.metadata().getOrPut(VoyantePower.PLAYERS_SAW_KEY, HashSet::new).add(target);

            picker.minecraft(player -> {
                player.sendMessage(
                        ChatColor.DARK_PURPLE.toString() + "Votre boule de cristal indique... que " +
                        ChatColor.BOLD + target.getName() +
                        ChatColor.DARK_PURPLE + " est " + ChatColor.BOLD + target.getCard().getName() +
                        ChatColor.DARK_PURPLE + "."
                );
                LGSoundStuff.enchant(player);
            });
        }

        private boolean isPowerAvailable(LGPlayer picker) {
            return !playersWhoLooked.contains(picker);
        }

        private boolean isVoyante(LGPlayer player) {
            return player.powers().has(VoyantePower.class);
        }
    }
}
