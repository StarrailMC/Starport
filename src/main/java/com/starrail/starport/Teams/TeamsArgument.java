package com.starrail.starport.Teams;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.starrail.starport.Data.Rank;
import io.papermc.paper.command.brigadier.MessageComponentSerializer;
import io.papermc.paper.command.brigadier.argument.CustomArgumentType;
import net.kyori.adventure.text.Component;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public class TeamsArgument implements CustomArgumentType<Rank, String> {

    @Override
    public Rank parse(StringReader reader) throws CommandSyntaxException {
        String input = reader.readUnquotedString();

        try {
            return Rank.valueOf(input.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new com.mojang.brigadier.exceptions.SimpleCommandExceptionType(() -> "Invalid Rank" + input).create();
        }
    }

    private static final DynamicCommandExceptionType ERROR_INVALID_RANK = new DynamicCommandExceptionType(rank ->
            MessageComponentSerializer.message().serialize(
                    Component.text(rank + " is not a team!")
            )
    );

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        String inputLower = builder.getRemainingLowerCase();

        for (Rank rank : Rank.values()) {
            String name = rank.toString();
            if (name.toLowerCase(Locale.ROOT).startsWith(inputLower)) {
                builder.suggest(name);
            }
        }

        return builder.buildFuture();
    }

    @Override
    public ArgumentType<String> getNativeType() {
        return StringArgumentType.word();
    }

}
