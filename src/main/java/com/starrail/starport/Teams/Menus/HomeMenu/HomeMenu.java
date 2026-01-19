package com.starrail.starport.Teams.Menus.HomeMenu;

import com.starrail.starport.Starport;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;


public class HomeMenu implements InventoryHolder {


    private final Starport plugin;
    private final Player player;
    private Inventory setInventory;
    public NamespacedKey buttonID;





    public Inventory TeamMenu() {

        Starport plugin = this.plugin;
        Player player = this.player;

        Component title = MiniMessage.miniMessage().deserialize("<shift:-48><glyph:guild_menu>");
        Inventory inventory = plugin.getServer().createInventory(this, 27, title);

        Component rank = plugin.getRankManager().getRank(player.getUniqueId()).component();
        Component playerName = Component.text(player.getName());

        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();
        playerHeadMeta.customName(Component.textOfChildren(rank, playerName).decoration(TextDecoration.ITALIC, false));
        playerHeadMeta.setItemModel(new NamespacedKey("nexo", "2d_player_head"));
        playerHeadMeta.lore();
        playerHeadMeta.setOwningPlayer(player);
        playerHead.setItemMeta(playerHeadMeta);

        inventory.setItem(13, playerHead);

        this.setInventory = inventory;
        return inventory;

    }

    public Inventory TeamMenuNoTeam() {

        Starport plugin = this.plugin;
        Player player = this.player;

        Component title = MiniMessage.miniMessage().deserialize("<shift:-48><glyph:guild_menu_no_team>");
        Inventory inventory = plugin.getServer().createInventory(this, 27, title);

        Component rank = plugin.getRankManager().getRank(player.getUniqueId()).component();
        Component playerName = Component.text(player.getName());

        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();
        playerHeadMeta.customName(Component.textOfChildren(rank, playerName).decoration(TextDecoration.ITALIC, false));
        playerHeadMeta.setItemModel(new NamespacedKey("nexo", "2d_player_head"));
        playerHeadMeta.setOwningPlayer(player);
        playerHead.setItemMeta(playerHeadMeta);

        ItemStack createGuild = new ItemStack(Material.PAPER);
        ItemMeta createGuildMeta = createGuild.getItemMeta();
        createGuildMeta.customName(MiniMessage.miniMessage().deserialize("<green><bold>Create a Guild!").decoration(TextDecoration.ITALIC, false));
        createGuildMeta.setItemModel(new NamespacedKey("minecraft", "air"));
        createGuildMeta.getPersistentDataContainer().set(this.buttonID, PersistentDataType.STRING, "createGuild");
        createGuild.setItemMeta(createGuildMeta);

        ItemStack findGuild = new ItemStack(Material.PAPER);
        ItemMeta findGuildMeta = findGuild.getItemMeta();
        findGuildMeta.customName(MiniMessage.miniMessage().deserialize("<gold><bold>Find a Guild!").decoration(TextDecoration.ITALIC, false));
        findGuildMeta.setItemModel(new NamespacedKey("minecraft", "air"));
        findGuildMeta.getPersistentDataContainer().set(this.buttonID, PersistentDataType.STRING, "findGuild");
        findGuild.setItemMeta(findGuildMeta);

        inventory.setItem(16, findGuild);
        inventory.setItem(13, playerHead);
        inventory.setItem(10, createGuild);

        this.setInventory = inventory;
        return inventory;

    }

    public Inventory CreateTeam(String padlockGlyph, Component teamName) {


        Starport plugin = this.plugin;
        Player player = this.player;
        String lockGlyph;
        String lockText = "";
        if ("locked".equals(padlockGlyph)) {
            lockGlyph = "<glyph:locked_button>";
            lockText = "<red><bold>Private";
        } else if ("unlocked".equals(padlockGlyph)) {
            lockGlyph = "<glyph:open_button>";
            lockText = "<green><bold>Public";
        } else {
            lockGlyph = "";
        }

        Component title = MiniMessage.miniMessage().deserialize("<shift:-48><glyph:create_team><shift:-152>"+lockGlyph);
        Inventory inventory = plugin.getServer().createInventory(this, 27, title);

        Component rank = plugin.getRankManager().getRank(player.getUniqueId()).component();
        Component playerName = Component.text(player.getName());

        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();
        playerHeadMeta.customName(Component.textOfChildren(rank, playerName).decoration(TextDecoration.ITALIC, false));
        playerHeadMeta.setItemModel(new NamespacedKey("nexo", "2d_player_head"));
        playerHeadMeta.setOwningPlayer(player);
        playerHead.setItemMeta(playerHeadMeta);

        ItemStack createGuild = new ItemStack(Material.PAPER);
        ItemMeta createGuildMeta = createGuild.getItemMeta();
        createGuildMeta.customName(MiniMessage.miniMessage().deserialize("<green><bold>Create a Guild!").decoration(TextDecoration.ITALIC, false));
        createGuildMeta.setItemModel(new NamespacedKey("minecraft", "air"));
        createGuildMeta.getPersistentDataContainer().set(this.buttonID, PersistentDataType.STRING, "finishGuild");
        createGuild.setItemMeta(createGuildMeta);

        ItemStack guildName = new ItemStack(Material.PAPER);
        ItemMeta guildNameMeta = guildName.getItemMeta();
        Component namePrefix = MiniMessage.miniMessage().deserialize("<gold><bold>Name: <reset>");
        guildNameMeta.customName(Component.textOfChildren(namePrefix, teamName).decoration(TextDecoration.ITALIC, false));
        guildNameMeta.setItemModel(new NamespacedKey("minecraft", "air"));
        guildNameMeta.getPersistentDataContainer().set(this.buttonID, PersistentDataType.STRING, "nameGuild");
        guildName.setItemMeta(guildNameMeta);

        ItemStack cancel = new ItemStack(Material.PAPER);
        ItemMeta cancelMeta = cancel.getItemMeta();
        cancelMeta.customName(MiniMessage.miniMessage().deserialize("<red><bold>Cancel").decoration(TextDecoration.ITALIC, false));
        cancelMeta.setItemModel(new NamespacedKey("minecraft", "air"));
        cancelMeta.getPersistentDataContainer().set(this.buttonID, PersistentDataType.STRING, "cancel");
        cancel.setItemMeta(cancelMeta);

        ItemStack openButton = new ItemStack(Material.PAPER);
        ItemMeta openButtonMeta = openButton.getItemMeta();
        openButtonMeta.customName(MiniMessage.miniMessage().deserialize(lockText).decoration(TextDecoration.ITALIC, false));
        openButtonMeta.setItemModel(new NamespacedKey("minecraft", "air"));
        openButtonMeta.getPersistentDataContainer().set(this.buttonID, PersistentDataType.STRING, "openGuild");
        openButton.setItemMeta(openButtonMeta);

        inventory.setItem(1, openButton);
        inventory.setItem(10, guildName);
        inventory.setItem(13, playerHead);
        inventory.setItem(16, createGuild);
        inventory.setItem(26, cancel);

        this.setInventory = inventory;
        return inventory;

    }

    public HomeMenu(Starport plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
        this.buttonID = new NamespacedKey(this.plugin, "buttonID");

    }

    @Override
    public Inventory getInventory() {
        return this.setInventory;
    }

}
