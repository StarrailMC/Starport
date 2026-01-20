package net.starrailsmp.starport.Integrations;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class UltimateTeams {

    public static String getPrefix(Player player) {
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            return "";
        }
        String placeholder = "%ultimateteams_team_prefix%";
        String prefix = PlaceholderAPI.setPlaceholders(player, placeholder);
        if (prefix == null ||
                prefix.isEmpty() ||
                prefix.equals(placeholder) ||
                prefix.equalsIgnoreCase("null") ||
                prefix.equalsIgnoreCase("Not in a Team")) {
            return "";
        }
        return prefix;
    }
}
