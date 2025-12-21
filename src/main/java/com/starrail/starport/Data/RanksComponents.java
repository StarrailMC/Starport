package com.starrail.starport.Data;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public record RanksComponents() {

    public static final Component Owner = Component.text("[ Owner ] ", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD);
    public static final Component CoOwner = Component.text("[ Co-Owner ] ", NamedTextColor.DARK_RED, TextDecoration.BOLD);
    public static final Component Developer = Component.text("[ Developer ] ", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD);
    public static final Component Admin = Component.text("[ Admin ] ", NamedTextColor.GREEN, TextDecoration.BOLD);
    public static final Component Moderator = Component.text("[ Moderator ] ", NamedTextColor.AQUA, TextDecoration.BOLD);
    public static final Component Member = Component.text("[ Member ] ", NamedTextColor.BLUE, TextDecoration.BOLD);

}
