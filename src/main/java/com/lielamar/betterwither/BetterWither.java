package com.lielamar.betterwither;

import com.lielamar.betterwither.handlers.ConfigHandler;
import com.lielamar.betterwither.handlers.WitherHandler;
import com.lielamar.betterwither.listeners.OnWitherEvents;
import com.lielamar.betterwither.modules.CustomWither;
import com.packetmanager.lielamar.PacketManager;
import net.minecraft.server.v1_8_R3.EntityWither;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BetterWither extends JavaPlugin {

    private ConfigHandler configHandler;
    private WitherHandler witherHandler;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        registerListeners();
        registerManagers();
    }

    public void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new OnWitherEvents(this), this);
    }

    public void registerManagers() {
        PacketManager.registerEntity("customwither", 64, EntityWither.class, CustomWither.class);

        this.configHandler = new ConfigHandler(this);
        this.witherHandler = new WitherHandler();
    }

    public ConfigHandler getConfigHandler() {
        return this.configHandler;
    }

    public WitherHandler getWitherHandler() {
        return this.witherHandler;
    }
}
