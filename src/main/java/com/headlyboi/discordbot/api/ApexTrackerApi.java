package com.headlyboi.discordbot.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.headlyboi.discordbot.enums.Platform;
import com.headlyboi.discordbot.handler.DiscordChannelHandler;
import com.headlyboi.discordbot.util.PropertiesUtil;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

/**
 * API tracker.gg
 */
@Component
@RequiredArgsConstructor
public class ApexTrackerApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscordChannelHandler.class);

    private final PropertiesUtil propertiesUtil;

    private final RestTemplate restTemplate;

    public String getPlayerData(Platform platform, String id) {
        try {
            URI uri = new URI(String.format(propertiesUtil.getPlayerDataUrl(), platform.getValue(), id));
            String responseJson = restTemplate.getForObject(uri, String.class);

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        //TODO : доделать нормальный ответ
        return Strings.EMPTY;
    }

}
