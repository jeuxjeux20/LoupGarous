package com.github.jeuxjeux20.loupsgarous.config;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.objectmapping.Setting;
import org.spongepowered.configurate.serialize.ConfigSerializable;

import java.util.OptionalInt;

@ConfigSerializable
public final class WorldPoolConfiguration {
    @Setting(value = "min-worlds")
    private int minWorlds = 4;

    @Setting(value = "max-worlds")
    private @Nullable Integer maxWorlds = null;

    public int getMinWorlds() {
        return minWorlds;
    }

    public void setMinWorlds(int minWorlds) {
        this.minWorlds = maxWorlds == null ? minWorlds : Math.min(minWorlds, maxWorlds);
    }

    public OptionalInt getMaxWorlds() {
        return maxWorlds == null ? OptionalInt.empty() : OptionalInt.of(maxWorlds);
    }

    public void setMaxWorlds(@Nullable Integer maxWorlds) {
        this.maxWorlds = maxWorlds == null ? null : Math.max(minWorlds, maxWorlds);
    }
}