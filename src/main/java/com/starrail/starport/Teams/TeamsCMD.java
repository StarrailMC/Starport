package com.starrail.starport.Teams;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.starrail.starport.Starport;
import com.starrail.starport.Teams.Menus.HomeMenu.HomeMenu;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TeamsCMD {

    private final Starport plugin;

    public TeamsCMD(Starport plugin) {
        this.plugin = plugin;
    }

    public LiteralCommandNode<CommandSourceStack> guild() {
        return Commands.literal("guild")
                .then(Commands.literal("menu")
                        .executes(ctx -> {

                            Player player = (Player) ctx.getSource().getExecutor();
                            Inventory menu = new HomeMenu(plugin, player).TeamMenuNoTeam();
                            player.openInventory(menu);

                            return Command.SINGLE_SUCCESS;

                        }))
                .build();
    }

}



