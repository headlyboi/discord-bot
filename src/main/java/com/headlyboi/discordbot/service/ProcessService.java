package com.headlyboi.discordbot.service;

import com.headlyboi.discordbot.api.apex.ApexApi;
import com.headlyboi.discordbot.api.apex.dto.mozambique.MozambiqueWrapperDto;
import com.headlyboi.discordbot.enums.ApexRank;
import com.headlyboi.discordbot.enums.Platform;
import com.headlyboi.discordbot.handler.DiscordChannelHandler;
import com.headlyboi.discordbot.service.reply.ApexReplyService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscordChannelHandler.class);

    private final ApexApi<MozambiqueWrapperDto> mozambiqueTrackerApi;
    private final RoleService roleService;
    private final ApexReplyService apexReplyService;

    public void processApex(final SlashCommandInteractionEvent event) {
        SlashCommandInteraction commandInteraction = event.getInteraction();

        Platform platform = Platform.findPlatform(Objects.
                requireNonNull(commandInteraction.getOption("platform")).getAsString());
        String nickName = Objects.requireNonNull(commandInteraction.getOption("nickname")).getAsString();

        try {
            Optional<MozambiqueWrapperDto> playerData = mozambiqueTrackerApi.getPlayerData(platform, nickName);

            if (playerData.isEmpty()) {
                event.reply("Player " + nickName + " not found!").queue();
            } else {
                String repliedText = apexReplyService.replyStats(playerData.get());
                ApexRank apexRank = ApexRank.getApexRank(playerData.get().getGlobal().getRank().getRankName());
                event.reply(repliedText).queue();
                roleService.addRoleToUser(event, apexRank);
            }
        } catch (HttpServerErrorException e) {
            event.reply("Player " + nickName + " not found!").queue();
        } catch (HttpClientErrorException e) {
            LOGGER.error("Error while fetching player data", e);
            event.reply("Server error. Pls contact administrator!").queue();
        }
    }
}
