package com.headlyboi.discordbot.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.headlyboi.discordbot.api.dto.ApexDataDto;
import com.headlyboi.discordbot.api.dto.ApexWrapperDataDto;
import com.headlyboi.discordbot.enums.Platform;
import com.headlyboi.discordbot.util.PropertiesUtil;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * API tracker.gg
 */
@Component
@RequiredArgsConstructor
public class ApexTrackerApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApexTrackerApi.class);

    private final PropertiesUtil propertiesUtil;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getPlayerData(Platform platform, String id) {
        try {
            URI uri = new URI(String.format(propertiesUtil.getPlayerDataUrl(), platform.getValue(), id));
            String responseJson = restTemplate.getForObject(uri, String.class);
            ApexWrapperDataDto apexStats = objectMapper.readValue(responseJson, ApexWrapperDataDto.class);
            return objectMapper.writeValueAsString(apexStats);
        } catch (URISyntaxException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
