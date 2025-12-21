package com.starrail.starport.Listeners;

import com.starrail.starport.Starport;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Random;

public class JoinListener implements Listener {

    private final Starport plugin;

    public JoinListener(Starport plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        Component rank = plugin.getRankManager().getRank(player.getUniqueId()).component();
        if (rank == null) rank = Component.empty();

        Component playerHead = MiniMessage.miniMessage().deserialize("<head:" + event.getPlayer().getName() + "> ");
        Component playerName = Component.text(player.getName());

        Random random = new Random();
        Component joinMessage;
        int randomInt = random.nextInt(1,3);
        if (randomInt == 1) {
            joinMessage = Component.text(" has joined the server");
        } else if (randomInt == 2) {
            joinMessage = Component.text(" has slid into the party");
        } else {
            joinMessage = Component.text(" is in the server now");
        }

        event.joinMessage(Component.textOfChildren(rank, playerHead, playerName, joinMessage));
    }
}

