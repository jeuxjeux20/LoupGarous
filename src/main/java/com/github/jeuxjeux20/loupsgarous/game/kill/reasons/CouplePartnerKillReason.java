package com.github.jeuxjeux20.loupsgarous.game.kill.reasons;

import com.github.jeuxjeux20.loupsgarous.game.LGPlayer;
import org.bukkit.ChatColor;

import java.util.Objects;

import static com.github.jeuxjeux20.loupsgarous.LGChatStuff.*;

public final class CouplePartnerKillReason extends SingleLGKillReason {
    private final LGPlayer partner;

    public CouplePartnerKillReason(LGPlayer partner) {
        this.partner = Objects.requireNonNull(partner, "partner is null");
    }

    @Override
    public String getKillMessage(LGPlayer player) {
        return killMessage("L'amour envers ") + ChatColor.ITALIC + partner.getName() + killMessage(" est tellement fort que ")
               + player(player.getName()) + killMessage(", ") + role(player.getCard().getName())
               + killMessage(" s'est suicidé.");
    }

    public LGPlayer getPartner() {
        return partner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouplePartnerKillReason that = (CouplePartnerKillReason) o;
        return Objects.equals(partner, that.partner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partner);
    }
}
