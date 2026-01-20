package net.starrailsmp.starport.Data;

import net.kyori.adventure.text.Component;

public enum Rank {
    OWNER, CO_OWNER, DEVELOPER, ADMIN, MODERATOR, UNION, CREATOR, OG, SUPPORTER, VIP, MEMBER;

    public Component component() {
        return switch (this) {
            case OWNER -> RanksComponents.OWNER;
            case CO_OWNER -> RanksComponents.CO_OWNER;
            case DEVELOPER -> RanksComponents.DEVELOPER;
            case ADMIN -> RanksComponents.ADMIN;
            case MODERATOR -> RanksComponents.MODERATOR;

            case UNION -> RanksComponents.UNION;
            case CREATOR -> RanksComponents.CREATOR;
            case OG -> RanksComponents.OG;
            case SUPPORTER -> RanksComponents.SUPPORTER;
            case VIP -> RanksComponents.VIP;
            case MEMBER -> RanksComponents.MEMBER;
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