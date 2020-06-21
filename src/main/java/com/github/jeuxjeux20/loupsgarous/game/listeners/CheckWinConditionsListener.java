package com.github.jeuxjeux20.loupsgarous.game.listeners;

import com.github.jeuxjeux20.loupsgarous.game.LGGameOrchestrator;
import com.github.jeuxjeux20.loupsgarous.game.endings.LGEnding;
import com.github.jeuxjeux20.loupsgarous.game.event.stage.LGStageStartingEvent;
import com.github.jeuxjeux20.loupsgarous.game.stages.LGStage;
import com.github.jeuxjeux20.loupsgarous.game.winconditions.PostponesWinConditions;
import com.github.jeuxjeux20.loupsgarous.game.winconditions.WinCondition;
import com.google.inject.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.Optional;
import java.util.Set;

public final class CheckWinConditionsListener implements Listener {
    private final Set<WinCondition> winConditions;

    @Inject
    CheckWinConditionsListener(Set<WinCondition> winConditions) {
        this.winConditions = winConditions;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onLGStageChange(LGStageStartingEvent event) {
        LGGameOrchestrator orchestrator = event.getOrchestrator();
        LGStage stage = event.getStage();

        if (!orchestrator.isGameRunning() ||
            stage.getClass().isAnnotationPresent(PostponesWinConditions.class)) {
            return;
        }

        for (WinCondition winCondition : winConditions) {
            Optional<LGEnding> ending = winCondition.check(orchestrator);
            if (ending.isPresent()) {
                orchestrator.finish(ending.get());
                return;
            }
        }
    }
}
