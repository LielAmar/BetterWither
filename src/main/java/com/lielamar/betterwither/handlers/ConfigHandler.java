package com.lielamar.betterwither.handlers;

import com.lielamar.betterwither.modules.WitherSize;
import com.lielamar.betterwither.modules.WitherStats;
import org.bukkit.plugin.Plugin;

import java.util.Random;

public class ConfigHandler {

    private final static Random rnd = new Random();

    private final Plugin plugin;

    private boolean randomWither;
    private int witherHealthRangeMin;
    private int witherHealthRangeMax;
    private double witherStrengthRangeMin;
    private double witherStrengthRangeMax;

    private WitherSize witherSize;
    private double witherStrength;
    private int witherHealth;

    public ConfigHandler(Plugin plugin) {
        this.plugin = plugin;

        reload();
    }

    /**
     * Reloads the config and sets up all variables
     */
    public void reload() {
        this.randomWither = plugin.getConfig().getBoolean("Random Wither Stats", true);
        this.witherHealthRangeMin = plugin.getConfig().getInt("Random Health Range.min", 200);
        this.witherHealthRangeMax = plugin.getConfig().getInt("Random Health Range.max", 500);
        if(this.witherHealthRangeMin <= 0) this.witherHealthRangeMin = 200;
        if(this.witherHealthRangeMax <= 0) this.witherHealthRangeMax = 500;
        this.witherStrengthRangeMin = plugin.getConfig().getDouble("Random Strength Range.min", 0.5);
        this.witherStrengthRangeMax = plugin.getConfig().getDouble("Random Strength Range.max", 2.5);
        if(this.witherStrengthRangeMin <= 0) this.witherStrengthRangeMin = 0.5;
        if(this.witherStrengthRangeMax <= 0) this.witherStrengthRangeMax = 2.5;

        this.witherSize = WitherSize.valueOf(plugin.getConfig().getString("Wither Size", "SMALL"));
        this.witherStrength = plugin.getConfig().getDouble("Wither Strength", 1.25);
        this.witherHealth = plugin.getConfig().getInt("Wither Health", 300);
    }

    public WitherStats getWitherStats() {
        if(!randomWither)
            return new WitherStats(getWitherHealth(), getWitherStrength(), getWitherSize());
        else {
            int randomHealth = rnd.nextInt(getWitherHealthRangeMax() - getWitherHealthRangeMin() - 1) + 1;
            double randomStrength = getWitherStrengthRangeMin() +
                    (getWitherStrengthRangeMax() - getWitherStrengthRangeMin()) * rnd.nextDouble();
            WitherSize randomSize = WitherSize.values()[rnd.nextInt(WitherSize.values().length)];

            return new WitherStats(randomHealth, randomStrength, randomSize);
        }
    }

    public WitherSize getWitherSize() {
        return witherSize;
    }

    public double getWitherStrength() {
        return witherStrength;
    }

    public int getWitherHealth() {
        return witherHealth;
    }

    public int getWitherHealthRangeMin() {
        return witherHealthRangeMin;
    }

    public int getWitherHealthRangeMax() {
        return witherHealthRangeMax;
    }

    public double getWitherStrengthRangeMin() {
        return witherStrengthRangeMin;
    }

    public double getWitherStrengthRangeMax() {
        return witherStrengthRangeMax;
    }
}
