package com.github.jeuxjeux20.loupsgarous.game.listeners;

import com.github.jeuxjeux20.loupsgarous.game.LGPlayer;
import com.github.jeuxjeux20.loupsgarous.game.event.LGKillEvent;
import com.github.jeuxjeux20.loupsgarous.game.kill.LGKill;
import com.github.jeuxjeux20.loupsgarous.game.powers.ChasseurPower;
import com.github.jeuxjeux20.loupsgarous.game.stages.ChasseurKillStage;
import com.google.inject.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class ChasseurKillOnDeathListener implements Listener {
    private final ChasseurKillStage.Factory chasseurStageFactory;

    @Inject
    ChasseurKillOnDeathListener(ChasseurKillStage.Factory chasseurStageFactory) {
        this.chasseurStageFactory = chasseurStageFactory;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onLGKill(LGKillEvent event) {
        for (LGKill kill : event.getKills()) {
            LGPlayer victim = kill.getVictim();

            if (victim.powers().has(ChasseurPower.class) && victim.isPresent()) {
                event.getOrchestrator().stages().insert(o -> chasseurStageFactory.create(o, victim));
            }
        }
    }
}
