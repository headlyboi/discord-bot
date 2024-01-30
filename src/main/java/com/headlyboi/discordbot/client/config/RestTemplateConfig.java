package com.headlyboi.discordbot.client.config;

import com.headlyboi.discordbot.client.interceptor.ClientHeadersInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

    private static final Duration TEN_SECONDS = Duration.ofSeconds(10);

    private final ClientHeadersInterceptor clientHeadersInterceptor;

    @Bean
    public RestTemplate restTemplateBean() {
        return new RestTemplateBuilder()
                .setReadTimeout(TEN_SECONDS)
                .setReadTimeout(TEN_SECONDS)
                .interceptors(clientHeadersInterceptor)
                .build();
    }

}
