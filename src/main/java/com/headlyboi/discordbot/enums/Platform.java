package com.headlyboi.discordbot.enums;

import lombok.Getter;

@Getter
public enum Platform {

    ORIGIN("origin"),
    STEAM("steam"),
    XBL("xbl"),
    PSN("psn");

    private final String value;

    Platform(String value) {
        this.value = value;
    }

    public static Platform findPlatform(String value) {
        for (Platform platform : Platform.values()) {
            if (value.equals(platform.getValue())) {
                return platform;
            }
        }
        return Platform.ORIGIN;
    }
}
