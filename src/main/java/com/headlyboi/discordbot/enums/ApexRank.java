package com.headlyboi.discordbot.enums;

import lombok.Getter;

@Getter
public enum ApexRank {

    ROOKIE("Rookie", ApexColor.BROWN),
    BRONZE("Bronze", ApexColor.BROWN),
    SILVER("Silver", ApexColor.GRAY),
    GOLD("Gold", ApexColor.YELLOW),
    PLATINUM("Platinum", ApexColor.AZURE),
    DIAMOND("Diamond", ApexColor.BLUE),
    MASTER("Master", ApexColor.PINK),
    PREDATOR("Predator", ApexColor.RED);

    private final String value;

    private final ApexColor apexColor;

    ApexRank(String value, ApexColor apexColor) {
        this.value = value;
        this.apexColor = apexColor;
    }

    public static ApexRank getApexRank(String rank) {
        if (rank == null) {
            return ROOKIE;
        }
        for (ApexRank apexRank : ApexRank.values()) {
            if (rank.contains(apexRank.value)){
                return apexRank;
            }
        }
        return ROOKIE;
    }
}
