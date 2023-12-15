package com.headlyboi.discordbot.handler;

import com.headlyboi.discordbot.api.apex.ApexTrackerApi;
import com.headlyboi.discordbot.api.apex.dto.ApexWrapperDataDto;
import com.headlyboi.discordbot.enums.Platform;
import com.headlyboi.discordbot.service.ChannelService;
import com.headlyboi.discordbot.service.CommandService;
import com.headlyboi.discordbot.service.reply.IBotReplyService;
import com.headlyboi.discordbot.service.RoleService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DiscordChannelHandler extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscordChannelHandler.class);

    private final ChannelService channelService;

    private final RoleService roleService;

    private final CommandService commandService;

    private final ApexTrackerApi apexTrackerApi;

    private final IBotReplyService replyService;

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
                Platform platform = Platform.findPlatform(Objects.
                        requireNonNull(interaction.getOption("platform")).getAsString());
                String nickName = Objects.requireNonNull(interaction.getOption("nickname")).getAsString();
                Optional<ApexWrapperDataDto> playerData = apexTrackerApi.getPlayerData(platform, nickName);

                if (playerData.isPresent()) {
                    event.reply(replyService.replyStats(playerData.get()))
                            .queue();
                    break;
                }
                event.reply("Player not found!").queue();
                break;

            case "valorant-stats":

                //TODO: do this in future
                event.reply("valorant api not found").queue();
                break;

            case "overwatch-stats":

                //TODO: do this in future
                event.reply("overwatch api not found").queue();
                break;
            default:
                event.reply("error").queue();
        }

    }
}
