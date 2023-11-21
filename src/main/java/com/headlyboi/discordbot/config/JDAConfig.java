package com.headlyboi.discordbot.config;

import com.headlyboi.discordbot.handler.DiscordChannelHandler;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JDAConfig {

    @Value("${discord.bot.token}")
    private String discordToken;

    private final DiscordChannelHandler discordChannelHandler;

    @Bean
    public JDA createJDABean() {
        return JDABuilder
                .createDefault(discordToken)
                .addEventListeners(discordChannelHandler)
                .build();
    }

}
