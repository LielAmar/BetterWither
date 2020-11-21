package com.lielamar.betterwither.modules;

import com.lielamar.betterwither.BetterWither;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class CustomWither extends EntityWither {

    private final WitherStats witherStats;

    public CustomWither(World world, WitherStats witherStats) {
        super(((CraftWorld)world).getHandle());

        this.witherStats = witherStats;

        if(this.witherStats.getSize().getSizeValue() >= 2000) {
            setCustomName("Dinnerbone");
            setCustomNameVisible(false);
        }

        // Setting the Wither's max health & health
        getAttributeInstance(GenericAttributes.maxHealth).setValue(witherStats.getHealth());
        setHealth(witherStats.getHealth());
    }

    @Override
    public int cl() {
        return 0;
    }

    /**
     * Spawning the custom Wither, sets it to "not charged" and starts a thread, updating the
     * Wither size since it grows every tick
     *
     * @param location   Location to spawn the entity to
     * @return           Bukkit Entity created
     */
    public Entity spawnCustomWither(Location location) {
        setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        ((CraftWorld)location.getWorld()).getHandle().addEntity(this, CreatureSpawnEvent.SpawnReason.CUSTOM);

        try {
            getDataWatcher().watch(10, 0);
        } catch(Exception ignored) { }

        /*
        Runnable running as long as the Wither is alive. It sets the size of the wither to the size of the WitherStats object
         */
        new BukkitRunnable() {
            @Override
            public void run() {
                if(!isAlive() || getBukkitEntity() == null || getBukkitEntity().isDead()) {
                    cancel();
                    return;
                }

                r(witherStats.getSize().getSizeValue());
            }
        }.runTaskTimer(BetterWither.getPlugin(BetterWither.class), 0L, 20L);

        return getBukkitEntity();
    }
}
