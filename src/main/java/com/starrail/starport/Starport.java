package com.starrail.starport;

import com.starrail.starport.Commands.CoreCMD;
import com.starrail.starport.Listeners.JoinListener;
import com.starrail.starport.Listeners.LeaveListener;
import com.starrail.starport.Listeners.MessageListener;
import com.starrail.starport.Teams.Menus.HomeMenu.HomeMenuListener;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Starport extends JavaPlugin {

    public RankManager rankManager = new RankManager(this);

    public void registerEvent(Listener listener) { getServer().getPluginManager().registerEvents(listener, this); }

    @Override
    public void onEnable() {

        getLogger().info("Starport Initialized");

        rankManager.load();

        registerEvent(new JoinListener(this));
        registerEvent(new LeaveListener(this));
        registerEvent(new MessageListener(this));
        registerEvent(new HomeMenuListener(this));

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            Commands commands = event.registrar();

            CoreCMD coreCMD = new CoreCMD(this);

            commands.register(coreCMD.starport(),"The Core Starport Command for the Server");
        });
    }

    @Override
    public void onDisable() {
        if (rankManager != null) rankManager.save();
    }

    public RankManager getRankManager() {
        return rankManager;
    }
}
