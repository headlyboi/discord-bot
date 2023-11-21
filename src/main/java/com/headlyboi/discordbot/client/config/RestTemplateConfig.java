package com.headlyboi.discordbot.client.config;

import com.headlyboi.discordbot.client.interceptor.ClientHeadersInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    private static final Duration TEN_SECONDS = Duration.ofSeconds(10);

    @Bean
    public RestTemplate restTemplateBean(){
        return new RestTemplateBuilder()
                .setReadTimeout(TEN_SECONDS)
                .setReadTimeout(TEN_SECONDS)
                .interceptors(clientHeadersInterceptorBean())
                .build();
    }

    @Bean
    public ClientHeadersInterceptor clientHeadersInterceptorBean(){
        return new ClientHeadersInterceptor();
    }
}
