package com.github.jeuxjeux20.loupsgarous.game.scoreboard;

import com.github.jeuxjeux20.loupsgarous.game.LGGameOrchestrator;
import com.github.jeuxjeux20.loupsgarous.game.LGPlayer;
import com.github.jeuxjeux20.loupsgarous.game.cards.composition.util.CompositionFormatUtil;
import com.github.jeuxjeux20.loupsgarous.game.event.LGEvent;
import com.github.jeuxjeux20.loupsgarous.game.event.LGKillEvent;
import com.github.jeuxjeux20.loupsgarous.game.event.lobby.LGLobbyCompositionUpdateEvent;
import com.google.common.collect.ImmutableList;
import org.bukkit.ChatColor;

public class CompositionScoreboardComponent implements ScoreboardComponent {
    @Override
    public ImmutableList<Line> render(LGPlayer player, LGGameOrchestrator orchestrator) {
        ImmutableList.Builder<Line> lines = ImmutableList.builder();

        lines.add(new Line(ChatColor.AQUA.toString() + ChatColor.BOLD + "Composition"));

        String[] cardNames = CompositionFormatUtil.format(orchestrator.getCurrentComposition()).split("\n");

        for (String cardName : cardNames) {
            lines.add(new Line(cardName));
        }

        return lines.build();
    }

    @Override
    public ImmutableList<Class<? extends LGEvent>> getUpdateTriggers() {
        return ImmutableList.of(LGLobbyCompositionUpdateEvent.class, LGKillEvent.class);
    }
}
