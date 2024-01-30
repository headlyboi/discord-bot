package com.headlyboi.discordbot.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PropertiesUtil {

    @Value("${discord.channel.name}")
    private String channelName;

    @Value("${tracker.gg.token}")
    private String trackerToken;

    @Value("${discord.bot.token}")
    private String discordToken;

    @Value("${url.tracker.gg.player.data}")
    private String playerDataUrl;

    @Value("${apex.tracker.link}")
    private String apexTrackerLink;
}
