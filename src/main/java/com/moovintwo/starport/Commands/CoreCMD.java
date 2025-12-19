package com.moovintwo.starport.Commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class CoreCMD {


    public static LiteralCommandNode<CommandSourceStack> starport() {
        return Commands.literal("starport")
                .then(Commands.literal("version")
                        .executes(ctx -> {
                            ctx.getSource().getSender().sendMessage(MiniMessage.miniMessage().deserialize("<bold><blue>Starport</blue></bold> <red>Alpha 0.1</red>" +
                                    "<newline>Created by <head:Moovintwo:true> <hover:show_text:'I would link my Social Media,<newline>but I'm way too lazy'><gold>Moovintwo</gold>" +
                                    "<newline><gray>---------------------</gray>" +
                                    "<newline>This is the Core Plugin for Starrail SMP"));
                            return Command.SINGLE_SUCCESS;
                        }))
                .build();
    }

}
