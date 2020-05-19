package com.github.jeuxjeux20.loupsgarous.game.stages;

import com.github.jeuxjeux20.loupsgarous.game.LGGameOrchestrator;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import org.bukkit.ChatColor;

import static com.github.jeuxjeux20.loupsgarous.LGChatStuff.SKULL_SYMBOL;
import static com.github.jeuxjeux20.loupsgarous.LGChatStuff.info;

public class RevealAllKillsStage extends LogicLGGameStage {
    @Inject
    public RevealAllKillsStage(@Assisted LGGameOrchestrator orchestrator) {
        super(orchestrator);
    }

    @Override
    public void runSync() {
        if (orchestrator.getPendingKills().isEmpty()) {
            orchestrator.sendToEveryone(
                    ChatColor.BOLD + info(SKULL_SYMBOL + " Le village se lève... et personne n'est mort !")
            );
        }
        orchestrator.revealAllPendingKills();
    }
}