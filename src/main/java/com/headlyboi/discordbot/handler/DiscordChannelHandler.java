package com.headlyboi.discordbot.handler;

import com.headlyboi.discordbot.service.ChannelService;
import com.headlyboi.discordbot.service.CommandService;
import com.headlyboi.discordbot.service.ProcessService;
import com.headlyboi.discordbot.service.RoleBuilderService;
import com.headlyboi.discordbot.util.PropertiesUtil;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscordChannelHandler extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscordChannelHandler.class);

    private final ChannelService channelService;

    private final RoleBuilderService roleBuilderService;

    private final ProcessService processService;

    private final CommandService commandService;


    private final PropertiesUtil propertiesUtil;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        boolean isCorrectChannel = propertiesUtil.getChannelName().equals(event.getChannel().getName());
        boolean isBot = event.getMessage().getAuthor().isBot();
        if (isCorrectChannel && !isBot) {
            event.getMessage().delete().queue();
        }
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        LOGGER.info("GuildJoinEvent: {}", event.getGuild());
        channelService.createTextChannel(event);
        roleBuilderService.buildRoles(event);
        commandService.updateCommands(event);
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        LOGGER.info("GuildReadyEvent: {}", event.getGuild());
        channelService.createTextChannel(event);
        roleBuilderService.buildRoles(event);
        commandService.updateCommands(event);
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        boolean isCorrectChannel = propertiesUtil.getChannelName().equals(event.getChannel().getName());

        if (isCorrectChannel) {
            SlashCommandInteraction interaction = event.getInteraction();
            switch (interaction.getName()) {
                case "apex-stats" -> processService.processApex(event);
                case "valorant-stats" -> event.reply("valorant api not found").queue();
                case "overwatch-stats" -> event.reply("overwatch api not found").queue();
                default -> event.reply("error").queue();
            }
        } else {
            event.reply("Message on this channel not allowed").queue();
        }
    }
}
