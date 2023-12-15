package com.headlyboi.discordbot.api.apex;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.headlyboi.discordbot.api.apex.dto.ApexWrapperDataDto;
import com.headlyboi.discordbot.enums.Platform;
import com.headlyboi.discordbot.util.PropertiesUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

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

    public Optional<ApexWrapperDataDto> getPlayerData(Platform platform, String id) {
        try {
            URI uri = new URI(String.format(propertiesUtil.getPlayerDataUrl(), platform.getValue(), id));
            String responseJson = restTemplate.getForObject(uri, String.class);
            ApexWrapperDataDto apexWrapperDataDto = objectMapper.readValue(responseJson, ApexWrapperDataDto.class);
            apexWrapperDataDto.setLink(String.format(propertiesUtil.getApexTrackerLink(), platform.getValue(), id));

            return Optional.of(apexWrapperDataDto);
        } catch (URISyntaxException | JsonProcessingException e) {
            return Optional.empty();
        }
    }

}
