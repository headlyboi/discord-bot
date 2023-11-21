package com.headlyboi.discordbot.api;

import com.headlyboi.discordbot.enums.Platform;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
@RequiredArgsConstructor
public class ApexTrackerApi {


    @Value("${url.tracker.gg.player.data}")
    private String playerDataUrl;

    private final RestTemplate restTemplate;

    public String getPlayerData(Platform platform, String id) throws URISyntaxException {
        URI uri = new URI(String.format(playerDataUrl, platform.getValue(), id));
        return restTemplate.getForObject(uri, String.class);
    }

}
