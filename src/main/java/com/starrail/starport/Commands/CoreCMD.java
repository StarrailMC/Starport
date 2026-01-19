package com.starrail.starport.Commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.starrail.starport.Data.Rank;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import com.starrail.starport.Starport;

import java.util.Objects;

/**
 * Core command registration for /starport
 */
public class CoreCMD {

    private final Starport plugin;

    public CoreCMD(Starport plugin) {
        this.plugin = plugin;
    }



    public LiteralCommandNode<CommandSourceStack> starport() {
        return Commands.literal("starport")
                .requires(sender -> sender.getSender().isOp())
                // /starport version
                .then(Commands.literal("version")
                        .executes(ctx -> {
                            ctx.getSource().getSender().sendMessage(MiniMessage.miniMessage().deserialize(
                                    "<bold><blue>Starport</blue></bold> <red>Alpha 0.3</red>" +
                                            "<newline>Created by " +
                                            "<head:Moovintwo:true> <gold>Moovintwo</gold> and " +
                                            "<head:Deplo902:true> <gold>Deplo902</gold>" +
                                            "<newline><gray>---------------------</gray>" +
                                            "<newline>This is the Core Plugin for Starrail SMP"));
                            return Command.SINGLE_SUCCESS;
                        }))
                .then(Commands.literal("ranks")
                        // /starport ranks info
                        .then(Commands.literal("info")
                                .executes(ctx -> {
                                    ctx.getSource().getSender().sendMessage(MiniMessage.miniMessage().deserialize("<bold><blue>Starport Ranks</blue></bold>" +
                                            "<newline><gray>---------------------</gray>" +
                                            "<newline><gold>Owner</gold> - Full access to all server features and settings." +
                                            "<newline><gold>Co-Owner</gold> - Nearly full access, excluding ownership transfer." +
                                            "<newline><gold>Developer</gold> - Access to development tools and debugging." +
                                            "<newline><gold>Admin</gold> - Manage players and moderate the server." +
                                            "<newline><gold>Moderator</gold> - Assist in player management and enforce rules." +
                                            "<newline><gold>Member</gold> - Standard player with access to general features."));
                                    return Command.SINGLE_SUCCESS;
                                }))

                        // /starport ranks set <player> <rank>
                        .then(Commands.literal("set")
                                .then(Commands.argument("player", ArgumentTypes.player())
                                        .then(Commands.argument("rank", new RankArgument())
                                                .executes(ctx -> {
                                                    Player player = ctx.getArgument("player", PlayerSelectorArgumentResolver.class).resolve(ctx.getSource()).getFirst();
                                                    Rank rank = ctx.getArgument("rank", Rank.class);

                                                    plugin.getRankManager()
                                                                    .setRank(player.getUniqueId(), rank);

                                                    Component playerRankcomponent = plugin.getRankManager().getRank(player.getUniqueId()).component();
                                                    player.playerListName(Component.textOfChildren(playerRankcomponent, Component.text(player.getName())));

                                                    ctx.getSource().getSender().sendMessage(MiniMessage.miniMessage().deserialize(
                                                            "<green>Set " + player.getName() + "'s rank to " + rank.name() + "</green>"));
                                                    return Command.SINGLE_SUCCESS;
                                                })))))

                        .then(Commands.literal("nick")
                                .then(Commands.argument("player", ArgumentTypes.player())
                                        .then(Commands.argument("rank", new RankArgument())
                                                .then(Commands.argument("nickname", StringArgumentType.string())
                                                        .executes(ctx -> {
                                                            Player player = ctx.getArgument("player", PlayerSelectorArgumentResolver.class).resolve(ctx.getSource()).getFirst();
                                                            Rank rank = ctx.getArgument("rank", Rank.class);
                                                            String nickname = ctx.getArgument("nickname", String.class);

                                                            Component playerRankcomponent = plugin.getRankManager().getRank(player.getUniqueId()).component();

                                                            if (Objects.equals(nickname, "")) {
                                                                player.playerListName(Component.textOfChildren(playerRankcomponent, Component.text(player.getName())));

                                                                ctx.getSource().getSender().sendMessage(MiniMessage.miniMessage().deserialize(
                                                                        "<green>Cleared " + player.getName() + "'s nickname" + "</green>"));


                                                            } else {
                                                                player.playerListName(Component.textOfChildren(rank.component(), Component.text(nickname)));

                                                                ctx.getSource().getSender().sendMessage(MiniMessage.miniMessage().deserialize(
                                                                        "<green>Set " + player.getName() + "'s nickname to " + " [ " + rank.name() + " ] " + nickname + "</green>"));
                                                            }
                                                            return Command.SINGLE_SUCCESS;
                                                        })))))

                .build();
    }

}
