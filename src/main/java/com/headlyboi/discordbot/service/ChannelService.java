package com.headlyboi.discordbot.service;

import com.headlyboi.discordbot.util.PropertiesUtil;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private final PropertiesUtil propertiesUtil;

    public void createTextChannel(GenericGuildEvent event) {
        Guild guild = event.getGuild();
        List<GuildChannel> channels = guild.getChannels();
        boolean contains = false;
        for (GuildChannel channel : channels) {
            if (ChannelType.TEXT.equals(channel.getType()) && propertiesUtil.getChannelName().equals(channel.getName())) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            guild.createTextChannel(propertiesUtil.getChannelName()).queue();
        }
    }
}
