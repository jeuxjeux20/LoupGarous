package com.github.jeuxjeux20.loupsgarous.game.events;

import com.github.jeuxjeux20.loupsgarous.game.LGGameOrchestrator;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class LGTurnChangeEvent extends LGEvent {
    private static final HandlerList handlerList = new HandlerList();

    public LGTurnChangeEvent(LGGameOrchestrator orchestrator) {
        super(orchestrator);
    }

    public static @NotNull HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }
}
