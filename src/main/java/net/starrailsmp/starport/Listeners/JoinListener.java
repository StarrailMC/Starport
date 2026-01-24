package net.starrailsmp.starport.Listeners;

import net.starrailsmp.starport.Integrations.UltimateTeams;
import net.starrailsmp.starport.Starport;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
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

        Component header = MiniMessage.miniMessage().deserialize(
                "<blue><bold>Starrail <bold>SMP</blue>" +
                        "<newline> <gray>Version</gray> <red>0.5 Alpha</red>" +
                        "<newline><gray>----------------------------------</gray>");

        Component footer = MiniMessage.miniMessage().deserialize(
                "<gray>----------------------------------</gray><newline>" +
                        "| <blue>starrailsmp.net</blue>" +
                        "  | We Own the Domain Now! :D |");

        player.sendPlayerListHeaderAndFooter(header, footer);





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

        if (UltimateTeams.getPrefix(player) != "") {

            Component deserializedPrefix = LegacyComponentSerializer.legacySection().deserialize(UltimateTeams.getPrefix(player));
            Component teamPrefix = Component.textOfChildren(Component.text("[", NamedTextColor.GRAY), Component.space(), deserializedPrefix, Component.space(), Component.text("]", NamedTextColor.GRAY), Component.space());

            event.joinMessage(Component.textOfChildren(teamPrefix, rank, playerHead, playerName, joinMessage));
            player.playerListName(Component.textOfChildren(teamPrefix, rank, playerHead, playerName));
        } else {
            event.joinMessage(Component.textOfChildren(rank, playerHead, playerName, joinMessage));
            player.playerListName(Component.textOfChildren(rank, playerHead, playerName));
        }
    }
}

