package com.headlyboi.discordbot.handler;

import com.headlyboi.discordbot.api.ApexTrackerApi;
import com.headlyboi.discordbot.client.interceptor.ClientHeadersInterceptor;
import com.headlyboi.discordbot.enums.Platform;
import com.headlyboi.discordbot.service.ChannelService;
import com.headlyboi.discordbot.service.CommandService;
import com.headlyboi.discordbot.service.RoleService;
import com.headlyboi.discordbot.util.PropertiesUtil;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DiscordChannelHandler extends ListenerAdapter {


    private static final Logger LOGGER = LoggerFactory.getLogger(DiscordChannelHandler.class);

    private final ChannelService channelService;
    private final RoleService roleService;
    private final CommandService commandService;
    private final ApexTrackerApi apexTrackerApi;

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        LOGGER.info("GuildJoinEvent: {}", event.getGuild());
        channelService.createTextChannel(event);
        roleService.buildRoles(event);
        commandService.updateCommands(event);
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        LOGGER.info("GuildReadyEvent: {}", event.getGuild());
        channelService.createTextChannel(event);
        roleService.buildRoles(event);
        commandService.updateCommands(event);
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        SlashCommandInteraction interaction = event.getInteraction();
        switch (interaction.getName()) {
            case "apex-stats":
                String platform = Objects.requireNonNull(interaction.getOption("platform")).getAsString();
                String nickName = Objects.requireNonNull(interaction.getOption("nickname")).getAsString();
                event.reply(apexTrackerApi.getPlayerData(Platform.ORIGIN, nickName)).queue();
                break;
            default:
                event.reply("error").queue();
        }

    }
}
