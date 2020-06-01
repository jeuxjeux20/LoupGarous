package com.github.jeuxjeux20.loupsgarous.game;

import com.github.jeuxjeux20.loupsgarous.game.cards.LGCardsModule;
import com.github.jeuxjeux20.loupsgarous.game.chat.LGChatModule;
import com.github.jeuxjeux20.loupsgarous.game.commands.LGCommandsModule;
import com.github.jeuxjeux20.loupsgarous.game.inventory.LGInventoryModule;
import com.github.jeuxjeux20.loupsgarous.game.listeners.LGListenersModule;
import com.github.jeuxjeux20.loupsgarous.game.lobby.LGLobbyModule;
import com.github.jeuxjeux20.loupsgarous.game.scoreboard.LGScoreboardModule;
import com.github.jeuxjeux20.loupsgarous.game.signs.LGSignsModule;
import com.github.jeuxjeux20.loupsgarous.game.stages.LGStagesModule;
import com.github.jeuxjeux20.loupsgarous.game.teams.LGTeamsModule;
import com.github.jeuxjeux20.loupsgarous.game.winconditions.LGWinConditionsModule;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public final class LGGameModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new LGCardsModule());
        install(new LGListenersModule());
        install(new LGStagesModule());
        install(new LGCommandsModule());
        install(new LGChatModule());
        install(new LGScoreboardModule());
        install(new LGLobbyModule());
        install(new LGWinConditionsModule());
        install(new LGTeamsModule());
        install(new LGSignsModule());
        install(new LGInventoryModule());

        bind(LGGameManager.class).to(MinecraftLGGameManager.class);

        install(new FactoryModuleBuilder()
                .implement(LGGameOrchestrator.class, MinecraftLGGameOrchestrator.class)
                .build(LGGameOrchestrator.Factory.class));

        bind(LGActionBarManager.class).to(MinecraftLGActionBarManager.class);
    }
}