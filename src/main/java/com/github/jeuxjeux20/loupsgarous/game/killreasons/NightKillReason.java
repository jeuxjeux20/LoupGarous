package com.github.jeuxjeux20.loupsgarous.game.killreasons;

import com.github.jeuxjeux20.loupsgarous.LGChatStuff;
import com.github.jeuxjeux20.loupsgarous.game.LGKill;
import com.github.jeuxjeux20.loupsgarous.game.LGPlayer;
import com.github.jeuxjeux20.loupsgarous.util.WordingUtils;

import java.util.List;

import static com.github.jeuxjeux20.loupsgarous.LGChatStuff.*;

public final class NightKillReason extends MultiLGKillReason {
    @Override
    public String getKillMessage(List<LGKill> kills) {
        return killMessage("Le village se lève... sans ") +
               WordingUtils.joiningCommaAnd(kills.stream().map(LGKill::getWhoDied), this::role) +
               killMessage(".");
    }

    private String role(LGPlayer player) {
        return player(player.getName()) + killMessage(", qui était ") + LGChatStuff.role(player.getCard().getName());
    }
}