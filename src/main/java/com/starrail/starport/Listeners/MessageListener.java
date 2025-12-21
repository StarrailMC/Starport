package com.starrail.starport.Listeners;

import com.starrail.starport.Starport;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;


public class MessageListener implements Listener {

    private final Starport plugin;

    public MessageListener(Starport plugin) {
        this.plugin = plugin;
    }


    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerMessage(AsyncChatEvent event) {

        event.renderer((player, playerName, message, viewer) -> {
            Component rank = plugin.getRankManager().getRank(player.getUniqueId()).component();
            if (rank == null) rank = Component.empty();

            Component playerHead = MiniMessage.miniMessage().deserialize("<head:" + player.getName() + "> ");

            Component nameComp = Component.text(player.getName());
            Component separator = Component.text(" | ", NamedTextColor.GRAY);

            return Component.textOfChildren(rank, playerHead, nameComp, separator, message);
        });

    }

}
