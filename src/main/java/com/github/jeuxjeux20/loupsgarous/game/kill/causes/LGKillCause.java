package com.github.jeuxjeux20.loupsgarous.game.kill.causes;

import com.github.jeuxjeux20.loupsgarous.game.LGPlayer;

import java.util.Set;

public abstract class LGKillCause {
    /**
     * Gets the message displayed when the specified players die.
     *
     * @param players the players
     * @return the message to show
     */
    public abstract String getKillMessage(Set<LGPlayer> players);
}
