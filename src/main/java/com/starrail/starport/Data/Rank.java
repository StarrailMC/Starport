package com.starrail.starport.Data;

import net.kyori.adventure.text.Component;

public enum Rank {
    Owner, Co_Owner, Developer, Admin, Moderator, Union, Creator, OG, Supporter, VIP, Member;

    public Component component() {
        return switch (this) {
            case Owner -> RanksComponents.Owner;
            case Co_Owner -> RanksComponents.CoOwner;
            case Developer -> RanksComponents.Developer;
            case Admin -> RanksComponents.Admin;
            case Moderator -> RanksComponents.Moderator;

            case Union -> RanksComponents.Union;
            case Creator -> RanksComponents.Creator;
            case OG -> RanksComponents.OG;
            case Supporter -> RanksComponents.Supporter;
            case VIP -> RanksComponents.VIP;
            case Member -> RanksComponents.Member;
        };
    }

    public static Rank fromString(String s) {
        if (s == null) return Member;
        try {
            return Rank.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return Member;
        }
    }
}