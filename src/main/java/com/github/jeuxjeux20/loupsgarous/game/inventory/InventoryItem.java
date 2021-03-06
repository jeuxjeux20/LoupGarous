package com.github.jeuxjeux20.loupsgarous.game.inventory;

import com.github.jeuxjeux20.loupsgarous.game.HasTriggers;
import com.github.jeuxjeux20.loupsgarous.game.LGGameOrchestrator;
import com.github.jeuxjeux20.loupsgarous.game.LGPlayer;
import org.bukkit.inventory.ItemStack;

public interface InventoryItem extends HasTriggers {
    boolean isShown(LGPlayer player, LGGameOrchestrator orchestrator);

    ItemStack getItemStack();

    void onClick(LGPlayer player, LGGameOrchestrator orchestrator);
}
