package com.github.jeuxjeux20.loupsgarous.game.stages;

import com.github.jeuxjeux20.loupsgarous.game.Countdown;
import com.github.jeuxjeux20.loupsgarous.game.LGGameOrchestrator;
import com.github.jeuxjeux20.loupsgarous.game.LGGameTurnTime;
import com.github.jeuxjeux20.loupsgarous.game.LGPlayer;
import com.github.jeuxjeux20.loupsgarous.game.killreasons.VillageVoteKillReason;
import com.github.jeuxjeux20.loupsgarous.game.stages.interaction.Votable;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.github.jeuxjeux20.loupsgarous.LGChatStuff.info;

@MajorityVoteShortensCountdown
public class VillageVoteStage extends AsyncLGGameStage implements Votable, DualCountdownStage {
    private final VoteState currentState;
    private final Countdown unmodifiedCountdown;
    private final TickEventCountdown countdown;

    @Inject
    VillageVoteStage(@Assisted LGGameOrchestrator orchestrator) {
        super(orchestrator);

        currentState = new VoteState(orchestrator, this);
        unmodifiedCountdown = new Countdown(orchestrator.getPlugin(), 90);
        countdown = new TickEventCountdown(this, unmodifiedCountdown.getTimer());
    }

    @Override
    public boolean shouldRun() {
        return orchestrator.getTurn().getTime() == LGGameTurnTime.DAY;
    }

    @Override
    public CompletableFuture<Void> run() {
        // Only two players? They'll vote each other and that's it.
        if (orchestrator.getGame().getAlivePlayers().count() <= 2) {
            unmodifiedCountdown.setTimer(30);
            countdown.setTimer(30);
            countdown.resetBiggestTimerValue();
        }

        unmodifiedCountdown.start();
        return cancelRoot(countdown.start(), f -> f.thenRun(this::computeVoteOutcome));
    }

    private void computeVoteOutcome() {
        LGPlayer playerWithMostVotes = currentState.getPlayerWithMostVotes();
        if (playerWithMostVotes != null) {
            orchestrator.killInstantly(playerWithMostVotes, VillageVoteKillReason::new);
        } else {
            orchestrator.sendToEveryone(info("Le village n'a pas pu se décider !"));
        }
    }

    @Override
    public VoteState getCurrentState() {
        return currentState;
    }

    @Override
    public @NotNull String getName() {
        return "Vote du village";
    }

    @Override
    public Optional<String> getTitle() {
        return Optional.of("Le village va voter");
    }

    @Override
    public Countdown getCountdown() {
        return countdown;
    }

    @Override
    public Countdown getUnmodifiedCountdown() {
        return unmodifiedCountdown;
    }

    @Override
    public String getIndicator() {
        return "vote pour tuer";
    }
}
