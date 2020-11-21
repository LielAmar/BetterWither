package com.lielamar.betterwither.handlers;

import com.lielamar.betterwither.modules.WitherStats;
import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class WitherHandler {

    private final Map<Entity, WitherStats> witherStats = new HashMap<>();

    public void addWither(Entity wither, WitherStats stats) {
        witherStats.put(wither, stats);
    }

    public void removeWither(Entity wither) {
        witherStats.remove(wither);
    }

    public WitherStats getWitherStats(Entity wither) {
        return witherStats.get(wither);
    }
}
