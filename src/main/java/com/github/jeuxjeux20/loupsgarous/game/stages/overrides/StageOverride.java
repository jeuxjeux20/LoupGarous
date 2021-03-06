package com.github.jeuxjeux20.loupsgarous.game.stages.overrides;

import com.github.jeuxjeux20.loupsgarous.game.LGGameOrchestrator;
import com.github.jeuxjeux20.loupsgarous.game.stages.RunnableLGStage;

public interface StageOverride {
    boolean shouldOverride(LGGameOrchestrator orchestrator);

    void onceComplete(LGGameOrchestrator orchestrator);

    Class<? extends RunnableLGStage> getStageClass();

    RunnableLGStage.Factory<?> getStageFactory();
}
