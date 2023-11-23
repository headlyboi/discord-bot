package com.headlyboi.discordbot.service;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {

    @Value("${discord.channel.name}")
    private String channelName;


    public void createTextChannel(GenericGuildEvent event) {
        Guild guild = event.getGuild();
        List<GuildChannel> channels = event.getGuild().getChannels();
        boolean contains = false;
        for (GuildChannel channel : channels) {
            if (ChannelType.TEXT.equals(channel.getType()) && channelName.equals(channel.getName())) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            guild.createTextChannel(channelName).queue();
        }
    }
}
