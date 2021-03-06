package com.github.jeuxjeux20.loupsgarous.game.chat;

import com.github.jeuxjeux20.loupsgarous.game.LGGameOrchestrator;
import com.github.jeuxjeux20.loupsgarous.game.LGGameTurnTime;
import com.github.jeuxjeux20.loupsgarous.game.LGPlayer;
import com.google.inject.Inject;

public class DayChatChannel extends AbstractLGChatChannel {
    @Inject
    protected DayChatChannel(LGGameOrchestrator orchestrator) {
        super(orchestrator);
    }

    @Override
    public String getName() {
        return "Jour";
    }

    @Override
    public boolean isNameDisplayed() {
        return false;
    }

    private boolean isAccessible() {
        return orchestrator.game().getTurn().getTime() == LGGameTurnTime.DAY &&
               orchestrator.isGameRunning();
    }

    @Override
    public boolean isReadable(LGPlayer recipient) {
        return isAccessible();
    }

    @Override
    public boolean isWritable(LGPlayer sender) {
        return sender.isAlive() && isAccessible();
    }
}
