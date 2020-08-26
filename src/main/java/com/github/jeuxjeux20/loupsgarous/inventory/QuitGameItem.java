package com.github.jeuxjeux20.loupsgarous.inventory;

import com.github.jeuxjeux20.loupsgarous.game.LGGameOrchestrator;
import com.github.jeuxjeux20.loupsgarous.game.LGPlayer;
import com.github.jeuxjeux20.loupsgarous.event.LGEvent;
import com.github.jeuxjeux20.loupsgarous.event.LGGameWaitingForPlayersEvent;
import com.github.jeuxjeux20.loupsgarous.event.player.LGPlayerJoinEvent;
import com.github.jeuxjeux20.loupsgarous.event.phase.LGPhaseStartingEvent;
import com.google.common.collect.ImmutableList;
import me.lucko.helper.item.ItemStackBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class QuitGameItem implements InventoryItem {
    @Override
    public boolean isShown(LGPlayer player, LGGameOrchestrator orchestrator) {
        return true;
    }

    @Override
    public ItemStack getItemStack() {
        return ItemStackBuilder.of(Material.RED_BED)
                .name(ChatColor.RED.toString() + ChatColor.BOLD + "Quitter la partie")
                .build();
    }

    @Override
    public void onClick(LGPlayer player, LGGameOrchestrator orchestrator) {
        orchestrator.leave(player);
    }

    @Override
    public ImmutableList<Class<? extends LGEvent>> getUpdateTriggers() {
        return ImmutableList.of(LGPhaseStartingEvent.class, LGGameWaitingForPlayersEvent.class, LGPlayerJoinEvent.class);
    }
}