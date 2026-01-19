package com.starrail.starport.Teams.Menus.HomeMenu;

import com.starrail.starport.Starport;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public class HomeMenuListener implements Listener {

    private final Starport plugin;
    public NamespacedKey buttonID;

    final Map<UUID, Boolean> lockToggle = new HashMap<>();
    final Map<UUID, Component> teamNameMap = new HashMap<>();


    public HomeMenuListener(Starport plugin) {
        this.plugin = plugin;
        this.buttonID = new NamespacedKey(this.plugin, "buttonID");
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        Inventory inventory = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();

        if (inventory == null || !(inventory.getHolder(false) instanceof HomeMenu homeMenu)) { return; }
        event.setCancelled(true);
        ItemStack clicked = event.getCurrentItem();

        if (clicked != null) {
            ItemMeta clickedMeta = clicked.getItemMeta();
            PersistentDataContainer clickedPDC = clickedMeta.getPersistentDataContainer();
            if (clickedPDC.has(this.buttonID, PersistentDataType.STRING) ) {

                String button = clickedPDC.get(this.buttonID, PersistentDataType.STRING);

                if ("createGuild".equals(button)) {
                    boolean unlocked = lockToggle.getOrDefault(player.getUniqueId(), false);
                    Component teamName = teamNameMap.getOrDefault(player.getUniqueId(), MiniMessage.miniMessage().deserialize("<blue>Cool Team"));

                    lockToggle.put(player.getUniqueId(), !unlocked);
                    teamNameMap.put(player.getUniqueId(), teamName);

                    player.openInventory(new HomeMenu(plugin, player).CreateTeam("unlocked", teamName));
                    player.playSound(player, Sound.ITEM_SPEAR_USE, 100, 1);
                } else if ("openGuild".equals(button)) {

                    boolean unlocked = lockToggle.getOrDefault(player.getUniqueId(), false);
                    Component teamName = teamNameMap.getOrDefault(player.getUniqueId(), MiniMessage.miniMessage().deserialize("<blue>Cool Team"));

                    player.openInventory(new HomeMenu(plugin, player).CreateTeam(unlocked ? "unlocked" : "locked", teamName));

                    lockToggle.put(player.getUniqueId(), !unlocked);

                    Logger.getLogger("").info(player.getName() + " toggle is now " + !unlocked);

                    player.playSound(player, unlocked ? Sound.BLOCK_IRON_DOOR_OPEN : Sound.BLOCK_IRON_DOOR_CLOSE, 100, 1);
                } else if ("findGuild".equals(button)) {
                    player.sendMessage(Component.text("Finding a Guild"));
                } else if ("cancel".equals(button)) {
                    player.openInventory(new HomeMenu(this.plugin, player).TeamMenuNoTeam());
                    player.playSound(player, Sound.ITEM_SPEAR_USE, 100, 1);
                } else if ("nameGuild".equals(button)) {

                }

            }
        }


    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        lockToggle.remove(event.getPlayer().getUniqueId());
    }


}
