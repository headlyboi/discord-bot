package com.headlyboi.discordbot.api.apex;

import com.headlyboi.discordbot.enums.Platform;

import java.util.Optional;

public interface ApexApi<DTO> {

     Optional<DTO> getPlayerData(Platform platform, String name);
}
