package com.headlyboi.discordbot.service;

import com.headlyboi.discordbot.api.apex.ApexTrackerApi;
import com.headlyboi.discordbot.api.apex.dto.ApexWrapperDataDto;
import com.headlyboi.discordbot.enums.ApexRank;
import com.headlyboi.discordbot.enums.Platform;
import com.headlyboi.discordbot.repository.ApexRepository;
import com.headlyboi.discordbot.service.reply.IBotReplyService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcessService {

    private final ApexTrackerApi apexTrackerApi;

    private final IBotReplyService replyService;

    private final RoleBuilderService roleBuilderService;

    private final ApexRepository apexRepository;

    public void processApex(final SlashCommandInteractionEvent event) {
        SlashCommandInteraction commandInteraction = event.getInteraction();

        Platform platform = Platform.findPlatform(Objects.
                requireNonNull(commandInteraction.getOption("platform")).getAsString());
        String nickName = Objects.requireNonNull(commandInteraction.getOption("nickname")).getAsString();

        try {
            Optional<ApexWrapperDataDto> playerData = apexTrackerApi.getPlayerData(platform, nickName);

            if (playerData.isEmpty()) {
                event.reply("Player " + nickName + " not found!").queue();
            } else {
                ApexWrapperDataDto apexDataDto = playerData.get();
                event.reply(replyService.replyStats(apexDataDto)).queue();
                ApexRank apexRank = ApexRank.getApexRank(apexDataDto.getData().getOverview().orElseThrow()
                        .getStats().getRankScore().getMetadata().getRank());
                roleBuilderService.addRoleToUser(event, apexRank);
                apexRepository.save(Objects.requireNonNull(event.getMember()).getIdLong(), apexDataDto);
            }
        } catch (HttpServerErrorException e) {
            event.reply("Player " + nickName + " not found!").queue();
        }
    }
}
