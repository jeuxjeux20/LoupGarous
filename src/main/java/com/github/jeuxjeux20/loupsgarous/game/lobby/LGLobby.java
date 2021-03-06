package com.github.jeuxjeux20.loupsgarous.game.lobby;

import com.github.jeuxjeux20.loupsgarous.game.OrchestratorDependent;
import com.github.jeuxjeux20.loupsgarous.game.LGPlayer;
import com.github.jeuxjeux20.loupsgarous.game.InternalLGGameOrchestrator;
import com.github.jeuxjeux20.loupsgarous.game.cards.composition.Composition;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Manages players entering in and out of a game, as well as the {@link Composition} of the game.
 */
public interface LGLobby extends OrchestratorDependent {
    World getWorld();


    LGPlayer addPlayer(Player player) throws PlayerJoinException;

    boolean removePlayer(UUID playerUUID);

    default boolean removePlayer(OfflinePlayer player) {
        return removePlayer(player.getUniqueId());
    }

    default boolean removePlayer(LGPlayer player) {
        return removePlayer(player.getPlayerUUID());
    }


    boolean isLocked();


    /**
     * Gets the owner of the game.
     *
     * @return the owner of the game, or {@link LGPlayer#NULL} if there are no players
     */
    LGPlayer getOwner();

    void setOwner(LGPlayer owner);


    LGLobbyCompositionManager composition();


    default int getSlotsTaken() {
        return gameOrchestrator().game().getPlayers().size();
    }

    default int getTotalSlotCount() {
        return composition().get().getPlayerCount();
    }

    default boolean isFull() {
        return getSlotsTaken() == getTotalSlotCount();
    }

    default String getSlotsDisplay() {
        return "(" + getSlotsTaken() + "/" + getTotalSlotCount() + ")";
    }


    interface Factory {
        LGLobby create(LGGameBootstrapData lobbyInfo, InternalLGGameOrchestrator orchestrator)
                throws LobbyCreationException;
    }
}
