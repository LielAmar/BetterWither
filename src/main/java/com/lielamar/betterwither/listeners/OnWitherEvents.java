package com.lielamar.betterwither.listeners;

import com.lielamar.betterwither.BetterWither;
import com.lielamar.betterwither.modules.CustomWither;
import com.lielamar.betterwither.modules.WitherStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class OnWitherEvents implements Listener {

    private final BetterWither betterWither;
    public OnWitherEvents(BetterWither betterWither) {
        this.betterWither = betterWither;
    }

    @EventHandler
    public void witherSpawn(CreatureSpawnEvent event) {
        if(event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM) return;

        if(event.getEntity() == null || event.getEntity().getType() != EntityType.WITHER) return;
        event.setCancelled(true);

        WitherStats witherStats = betterWither.getConfigHandler().getWitherStats();
        CustomWither wither = new CustomWither(event.getLocation().getWorld(), witherStats);
        betterWither.getWitherHandler().addWither(wither.spawnCustomWither(event.getLocation()), witherStats);
    }

    @EventHandler
    public void witherDeath(EntityDeathEvent event) {
        betterWither.getWitherHandler().removeWither(event.getEntity());
    }

    @EventHandler
    public void witherDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager() == null) return;

        Wither wither;
        if(event.getDamager().getType() == EntityType.WITHER_SKULL) {
            WitherSkull witherSkull = (WitherSkull)event.getDamager();
            if(!(witherSkull.getShooter() instanceof Wither)) return;

            wither = (Wither)witherSkull.getShooter();
        } else if(event.getDamager().getType() == EntityType.WITHER) {
            wither = (Wither)event.getEntity();
        } else {
            return;
        }

        WitherStats witherStats = betterWither.getWitherHandler().getWitherStats(wither);
        if(witherStats == null) return;

        event.setDamage(event.getDamage() * witherStats.getStrength());
    }
}