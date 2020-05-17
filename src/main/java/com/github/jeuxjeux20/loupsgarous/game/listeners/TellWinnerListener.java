package com.github.jeuxjeux20.loupsgarous.game.listeners;

import com.github.jeuxjeux20.loupsgarous.LGChatStuff;
import com.github.jeuxjeux20.loupsgarous.game.events.LGGameFinishedEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TellWinnerListener implements Listener {
    @EventHandler
    public void onGameFinish(LGGameFinishedEvent event) {
        String endingMessage = event.getEnding().getMessage();

        String message = LGChatStuff.BANNER + "\n" +
                         ChatColor.GOLD + "La partie est terminée !\n" +
                         ChatColor.GOLD + ChatColor.BOLD + endingMessage + "\n" +
                         LGChatStuff.BANNER;

        event.getOrchestrator().sendToEveryone(message);
    }
}
