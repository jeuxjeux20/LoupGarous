package com.github.jeuxjeux20.loupsgarous.game.stages;

import com.github.jeuxjeux20.loupsgarous.LGSoundStuff;
import com.github.jeuxjeux20.loupsgarous.game.Countdown;
import com.github.jeuxjeux20.loupsgarous.game.LGGameOrchestrator;
import com.github.jeuxjeux20.loupsgarous.game.LGGameState;
import com.github.jeuxjeux20.loupsgarous.game.event.lobby.LGLobbyCompositionChangeEvent;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.concurrent.CompletableFuture;

public class GameStartStage extends RunnableLGStage implements CountdownTimedStage {
    private final Countdown countdown;

    @Inject
    GameStartStage(@Assisted LGGameOrchestrator orchestrator) {
        super(orchestrator);

        this.countdown = new GameStartCountdown();
    }

    @Override
    public CompletableFuture<Void> execute() {
        return countdown.start();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public Countdown getCountdown() {
        return countdown;
    }

    static class ResetTimerListener implements Listener {
        @EventHandler(ignoreCancelled = true)
        public void onLGLobbyCompositionChange(LGLobbyCompositionChangeEvent event) {
            LGStage currentStage = event.getOrchestrator().stages().current();
            if (currentStage instanceof GameStartStage) {
                GameStartStage stage = (GameStartStage) currentStage;
                stage.countdown.setTimer(stage.countdown.getBiggestTimerValue());
            }
        }
    }

    private class GameStartCountdown extends Countdown {
        public GameStartCountdown() {
            super(15);
        }

        @Override
        protected void onTick() {
            if (orchestrator.state() != LGGameState.READY_TO_START) {
                setTimer(getBiggestTimerValue());
            } else if (getTimer() != 0 && getTimer() <= 5) {
                orchestrator.getAllMinecraftPlayers().forEach(this::displayCountdown);
            }
        }

        private void displayCountdown(Player player) {
            player.sendTitle(ChatColor.YELLOW + String.valueOf(getTimer()), null, 3, 15, 3);
            LGSoundStuff.pling(player);
        }
    }
}
