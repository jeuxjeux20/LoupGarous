package com.github.jeuxjeux20.loupsgarous.game.cards;

import com.github.jeuxjeux20.loupsgarous.game.LGTeams;
import com.github.jeuxjeux20.loupsgarous.game.composition.validation.annotations.Unique;
import me.lucko.helper.item.ItemStackBuilder;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

@Unique
public final class VoyanteCard extends MutableLGCard {
    @Override
    protected String getMainTeam() {
        return LGTeams.VILLAGEOIS;
    }

    @Override
    public String getName() {
        return "Voyante";
    }

    @Override
    public String getPluralName() {
        return "Voyantes";
    }

    @Override
    public boolean isFeminineName() {
        return true;
    }

    @Override
    public String getDescription() {
        return "La voyante peut voir le role de n'importe qui ! C'est vraiment broken.";
    }

    @Override
    public ItemStack createGuiItem() {
        return ItemStackBuilder.of(Material.WHITE_BANNER)
                .transformMeta(m -> {
                    BannerMeta bannerMeta = (BannerMeta) m;

                    bannerMeta.addPattern(new Pattern(DyeColor.PURPLE, PatternType.GRADIENT));
                    bannerMeta.addPattern(new Pattern(DyeColor.PURPLE, PatternType.GRADIENT_UP));
                    bannerMeta.addPattern(new Pattern(DyeColor.PINK, PatternType.FLOWER));
                }).build();
    }
}
