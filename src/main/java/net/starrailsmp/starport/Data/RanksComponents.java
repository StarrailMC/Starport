package net.starrailsmp.starport.Data;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public record RanksComponents() {

    // Staff Roles
    public static final Component OWNER = Component.text("[ Owner ] ", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD);
    public static final Component CO_OWNER = Component.text("[ Co-Owner ] ", NamedTextColor.DARK_RED, TextDecoration.BOLD);
    public static final Component ADMIN = Component.text("[ Admin ] ", NamedTextColor.GREEN, TextDecoration.BOLD);
    public static final Component MODERATOR = Component.text("[ Moderator ] ", NamedTextColor.AQUA, TextDecoration.BOLD);

    public static final Component DEVELOPER = Component.text("[ Developer ] ", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD);
    public static final Component BUILDER = Component.text("[ Developer ] ", TextColor.color(255, 124, 25), TextDecoration.BOLD);


    public static final Component UNION = Component.text("[ The SSR ] ", NamedTextColor.RED, TextDecoration.BOLD);

    // Member Roles
    public static final Component CREATOR = Component.text("[ Content Creator ] ", NamedTextColor.RED, TextDecoration.BOLD);

    public static final Component OG = Component.text("[ Original Supporter ] ", NamedTextColor.GOLD, TextDecoration.BOLD);
    public static final Component SUPPORTER = Component.text("[ Supporter ] ", NamedTextColor.DARK_AQUA, TextDecoration.BOLD);
    public static final Component VIP = Component.text("[ VIP ] ", NamedTextColor.YELLOW, TextDecoration.BOLD);
    public static final Component MEMBER = Component.text("[ Member ] ", NamedTextColor.BLUE, TextDecoration.BOLD);

}
