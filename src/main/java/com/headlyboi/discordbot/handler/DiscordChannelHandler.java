package com.headlyboi.discordbot.handler;

import com.headlyboi.discordbot.client.interceptor.ClientHeadersInterceptor;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DiscordChannelHandler extends ListenerAdapter {

    private static final String TEXT_CHANNEL_STATS_FINDER = "stats-finder";

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientHeadersInterceptor.class);


    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        LOGGER.info("GuildJoinEvent: {}", event.getGuild());
        createTextChannel(event);
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        LOGGER.info("GuildReadyEvent: {}", event.getGuild());
        createTextChannel(event);
    }


    private void createTextChannel(GenericGuildEvent event) {
        Guild guild = event.getGuild();
        List<GuildChannel> channels = event.getGuild().getChannels();
        boolean contains = false;
        for (GuildChannel channel : channels) {
            System.out.println(channel);
            if (ChannelType.TEXT.equals(channel.getType()) && TEXT_CHANNEL_STATS_FINDER.equals(channel.getName())) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            guild.createTextChannel(TEXT_CHANNEL_STATS_FINDER).queue();
        }
    }
}
