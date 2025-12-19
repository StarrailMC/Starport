package com.moovintwo.starport;

import com.moovintwo.starport.Commands.CoreCMD;
import com.moovintwo.starport.Data.Ranks;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class Starport extends JavaPlugin {

    @Override
    public void onEnable() {

        getLogger().info("Starport Initialized");

        rankManager = new RankManager(this);
        rankManager.load();


        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new MessageListener(), this);

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            Commands commands = event.register();

            commands.register(
                    CoreCMD.starport(),
                    "The Core Starport Command for the Server"
            );
        });
    }

    @Override
    public void onDisable() {
        if (rankManager != null) rankManager.save();
    }
}
