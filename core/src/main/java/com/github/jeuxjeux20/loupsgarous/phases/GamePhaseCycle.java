package com.github.jeuxjeux20.loupsgarous.phases;

import com.github.jeuxjeux20.loupsgarous.extensibility.SortableContentFactory;
import com.github.jeuxjeux20.loupsgarous.game.LGGameOrchestrator;
import com.google.common.collect.ImmutableSet;
import io.reactivex.rxjava3.disposables.Disposable;

import static com.github.jeuxjeux20.loupsgarous.extensibility.LGExtensionPoints.PHASES;

public class GamePhaseCycle extends PhaseCycle {
    private final Disposable updateSubscription;

    public GamePhaseCycle(LGGameOrchestrator orchestrator) {
        super(orchestrator);

        updatePhases();
        updateSubscription = orchestrator.getGameBox().onChange()
                .filter(c -> !c.getContentsDiff(PHASES).isEmpty())
                .subscribe(c -> updatePhases());
    }

    private void updatePhases() {
        ImmutableSet<SortableContentFactory<? extends RunnableLGPhase>> phases =
                orchestrator.getGameBox().contents(PHASES);

        setPhases(phases);
    }

    @Override
    public void close() throws Exception {
        super.close();
        updateSubscription.dispose();
    }
}