package com.github.jeuxjeux20.loupsgarous.game.listeners;

import com.github.jeuxjeux20.loupsgarous.game.LGGameManager;
import com.github.jeuxjeux20.loupsgarous.game.LGPlayer;
import com.github.jeuxjeux20.loupsgarous.game.LGPlayerAndGame;
import com.google.inject.Inject;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Optional;

public class DeadPlayerAsSpectatorListener implements Listener {
    private final LGGameManager gameManager;

    @Inject
    DeadPlayerAsSpectatorListener(LGGameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Optional<LGPlayerAndGame> playerInGame = gameManager.getPlayerInGame(event.getPlayer());
        if (!playerInGame.isPresent()) return;

        Location spawnLocation = playerInGame.get().getOrchestrator().world().getSpawnLocation();
        event.setRespawnLocation(spawnLocation);

        Player player = event.getPlayer();

        LGPlayer lgPlayer = playerInGame.get().getPlayer();
        if (lgPlayer.isDead())
            player.setGameMode(GameMode.SPECTATOR);
    }
}
