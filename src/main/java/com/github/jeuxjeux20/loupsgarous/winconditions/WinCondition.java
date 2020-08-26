package com.github.jeuxjeux20.loupsgarous.winconditions;

import com.github.jeuxjeux20.loupsgarous.endings.LGEnding;
import com.github.jeuxjeux20.loupsgarous.game.LGGameOrchestrator;

import java.util.Optional;

public interface WinCondition {
    Optional<LGEnding> check(LGGameOrchestrator orchestrator);
}