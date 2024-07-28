package com.headlyboi.discordbot.api.apex;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.headlyboi.discordbot.api.apex.dto.mozambique.MozambiqueWrapperDto;
import com.headlyboi.discordbot.enums.Platform;
import com.headlyboi.discordbot.util.PropertiesUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

/**
 * API tracker.gg
 */
@Component
@RequiredArgsConstructor
public class MozambiqueTrackerApi implements ApexApi<MozambiqueWrapperDto>{

    private static final Logger LOGGER = LoggerFactory.getLogger(MozambiqueTrackerApi.class);

    private final PropertiesUtil propertiesUtil;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Optional<MozambiqueWrapperDto> getPlayerData(Platform platform, String name) {

        try {
            URI uri = new URI(String.format(propertiesUtil.getPlayerDataUrl(), propertiesUtil.getTrackerToken(), name, platform.getValue()));
            String responseJson = restTemplate.getForObject(uri, String.class);
            MozambiqueWrapperDto apexWrapperDataDto = objectMapper.readValue(responseJson, MozambiqueWrapperDto.class);

            return Optional.of(apexWrapperDataDto);
        } catch (Exception e) {
            LOGGER.error("Failed to get player data", e);
            return Optional.empty();
        }
    }
}
