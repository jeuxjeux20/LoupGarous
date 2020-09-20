package com.github.jeuxjeux20.loupsgarous.inventory;

import com.github.jeuxjeux20.loupsgarous.cards.composition.CompositionGui;
import com.github.jeuxjeux20.loupsgarous.event.LGEvent;
import com.github.jeuxjeux20.loupsgarous.event.LGGameStartEvent;
import com.github.jeuxjeux20.loupsgarous.event.lobby.LGOwnerChangeEvent;
import com.github.jeuxjeux20.loupsgarous.game.LGGameOrchestrator;
import com.github.jeuxjeux20.loupsgarous.game.LGPlayer;
import com.google.common.collect.ImmutableList;
import me.lucko.helper.item.ItemStackBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class EditLobbyItem implements InventoryItem {
    @Override
    public boolean isShown(LGPlayer player, LGGameOrchestrator orchestrator) {
        return orchestrator.getOwner() == player && orchestrator.allowsJoin();
    }

    @Override
    public ItemStack getItemStack() {
        return ItemStackBuilder.of(Material.EMERALD)
                .name(ChatColor.GREEN.toString() + ChatColor.BOLD + "Modifier la composition")
                .build();
    }

    @Override
    public void onClick(LGPlayer player, LGGameOrchestrator orchestrator) {
        player.minecraft(mcPlayer -> new CompositionGui(mcPlayer, orchestrator).open());
    }

    @Override
    public ImmutableList<Class<? extends LGEvent>> getUpdateTriggers() {
        return ImmutableList.of(LGOwnerChangeEvent.class, LGGameStartEvent.class);
    }
}