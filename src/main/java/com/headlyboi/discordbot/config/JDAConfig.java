package com.headlyboi.discordbot.config;

import com.headlyboi.discordbot.handler.DiscordChannelHandler;
import com.headlyboi.discordbot.util.PropertiesUtil;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JDAConfig {

    private final PropertiesUtil propertiesUtil;

    private final DiscordChannelHandler discordChannelHandler;

    @Bean
    public JDA createJDABean() {
        return JDABuilder
                .createDefault(propertiesUtil.getDiscordToken())
                .addEventListeners(discordChannelHandler)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();
    }

}
