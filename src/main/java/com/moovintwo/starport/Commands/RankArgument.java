package com.moovintwo.starport.Commands;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.papermc.paper.command.brigadier.CustomArgumentType;
import io.papermc.paper.command.brigadier.MessageComponentSerializer;
import net.kyori.adventure.text.Component;
import org.checkerframework.checker.nullness.qual.NullMarked;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

@NullMarked
public class RankArgument implements CustomArgumentType.Converted<Rank, String> {

    private static final DynamicCommandExceptionType ERROR_INVALID_RANK = new DynamicCommandExceptionType(rank -> 
        MessageComponentSerializer.message().serialize(
            Component.text(rank + " is not a rank!")
        )
    );

    @Override
    public Rank convert(String nativeType) throws CommandSyntaxException {
        try {
            return Rank.valueOf(nativeType.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ignored) {
            throw ERROR_INVALID_RANK.create(nativeType);
        }
    }

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