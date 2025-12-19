package com.moovintwo.starport;

import com.moovintwo.starport.Data.Ranks;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;


public class MessageListener implements Listener {


    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerMessage(AsyncChatEvent event) {

        event.renderer((player, playerName, message, viewer) -> {
            Component rank = Starport.rankManager.getRank(player.getUniqueId());
            if (rank == null) rank = Component.empty();

            Component playerHead = MiniMessage.miniMessage().deserialize("<head:" + player.getName() + "> ");

            Component nameComp = Component.text(player.getName());
            Component separator = Component.text(" | ", NamedTextColor.GRAY);

            return Component.textOfChildren(rank, playerHead, nameComp, separator, message);
        });

    }

}
