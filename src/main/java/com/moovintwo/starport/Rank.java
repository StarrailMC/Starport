package com.moovintwo.starport.Data;

import net.kyori.adventure.text.Component;

public enum Rank {
    OWNER, CO_OWNER, DEVELOPER, ADMIN, MODERATOR, MEMBER;

    public Component component() {
        return switch (this) {
            case OWNER -> RanksComponents.Owner;
            case CO_OWNER -> RanksComponents.CoOwner;
            case DEVELOPER -> RanksComponents.Developer;
            case ADMIN -> RanksComponents.Admin;
            case MODERATOR -> RanksComponents.Moderator;
            case MEMBER -> RanksComponents.Member;
        };
    }

    public static Rank fromString(String s) {
        if (s == null) return MEMBER;
        try {
            return Rank.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return MEMBER;
        }
    }
}