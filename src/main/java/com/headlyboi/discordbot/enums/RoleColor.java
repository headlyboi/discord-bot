package com.headlyboi.discordbot.enums;

import lombok.Getter;

import java.awt.Color;


@Getter
public enum RoleColor {

    BROWN(new Color(184, 145, 129)),
    GRAY(new Color(210, 210, 222)),
    YELLOW(new Color(249,217,158)),
    AZURE(new Color(109, 252, 255)),
    BLUE(new Color(37, 172, 255)),
    PINK(new Color(209,126,255)),
    RED(new Color(255,80,70));

    private final Color color;

    RoleColor(Color color) {
        this.color = color;
    }
}
