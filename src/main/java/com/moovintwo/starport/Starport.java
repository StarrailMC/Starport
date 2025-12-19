package com.moovintwo.starport;

import com.moovintwo.starport.Commands.CoreCMD;
import com.moovintwo.starport.Data.Ranks;
import com.moovintwo.starport.Listeners.JoinListener;
import com.moovintwo.starport.Listeners.MessageListener;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class Starport extends JavaPlugin {

    @Override
    public void onEnable() {

        getLogger().info("Starport Initialized");

        Ranks.init();


        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new MessageListener(), this);

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            Commands commands = event.registrar();

            commands.register(
                    CoreCMD.starport(),
                    "The Core Starport Command for the Server"
            );
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
