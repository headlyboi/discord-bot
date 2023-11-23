package com.headlyboi.discordbot.enums;

import lombok.Getter;

@Getter
public enum ApexRankRole {

    ROOKIE("Rookie", RoleColor.BROWN),
    BRONZE("Bronze", RoleColor.BROWN),
    SILVER("Silver", RoleColor.GRAY),
    GOLD("Gold", RoleColor.YELLOW),
    PLATINUM("Platinum", RoleColor.AZURE),
    DIAMOND("Diamond", RoleColor.BLUE),
    MASTER("Master", RoleColor.PINK),
    PREDATOR("Predator", RoleColor.RED);

    private final String name;

    private final RoleColor roleColor;

    ApexRankRole(String name, RoleColor roleColor) {
        this.name = name;
        this.roleColor = roleColor;
    }

}
