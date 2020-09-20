package com.github.jeuxjeux20.loupsgarous.event.phase;

import com.github.jeuxjeux20.loupsgarous.phases.LGPhase;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class LGPhaseEndedEvent extends LGPhaseEvent {
    private static final HandlerList handlerList = new HandlerList();

    public LGPhaseEndedEvent(LGPhase phase) {
        super(phase);
    }

    public static @NotNull HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }
}