package com.github.jeuxjeux20.loupsgarous.cards.revealers;

import com.github.jeuxjeux20.loupsgarous.game.LGGameOrchestrator;
import com.github.jeuxjeux20.loupsgarous.game.LGPlayer;
import com.github.jeuxjeux20.loupsgarous.teams.LGTeam;
import com.github.jeuxjeux20.loupsgarous.teams.LGTeams;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class CoupleCardRevealer implements CardRevealer {
    @Override
    public boolean willReveal(LGPlayer viewer, LGPlayer playerToReveal, LGGameOrchestrator orchestrator) {
        List<LGTeam> playerCouples = getCouples(playerToReveal);
        List<LGTeam> targetCouples = getCouples(viewer);

        return !Collections.disjoint(playerCouples, targetCouples);
    }

    private List<LGTeam> getCouples(LGPlayer player) {
        return player.teams().get().stream().filter(LGTeams::isCouple).collect(Collectors.toList());
    }
}