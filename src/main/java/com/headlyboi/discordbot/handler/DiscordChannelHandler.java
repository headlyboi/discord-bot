package com.headlyboi.discordbot.handler;

import com.headlyboi.discordbot.client.interceptor.ClientHeadersInterceptor;
import com.headlyboi.discordbot.service.ChannelService;
import com.headlyboi.discordbot.service.RoleService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscordChannelHandler extends ListenerAdapter {


    private static final Logger LOGGER = LoggerFactory.getLogger(ClientHeadersInterceptor.class);

    private final ChannelService channelService;
    private final RoleService roleService;

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        LOGGER.info("GuildJoinEvent: {}", event.getGuild());
        channelService.createTextChannel(event);
        roleService.buildRoles(event);
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        LOGGER.info("GuildReadyEvent: {}", event.getGuild());
        channelService.createTextChannel(event);
        roleService.buildRoles(event);
    }

}
