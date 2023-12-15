package com.headlyboi.discordbot.service;

import com.headlyboi.discordbot.enums.Platform;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommandService {

    public void updateCommands(GenericGuildEvent event) {
        Guild guild = event.getGuild();
        guild.updateCommands().addCommands(
                Commands.slash("apex-stats", "get rank from tracker.gg")
                        .addOptions(new OptionData(OptionType.STRING, "platform", "Origin/Steam/Xbl/Psn", true)
                                .addChoice("\uD83C\uDF51 Origin", Platform.ORIGIN.getValue())
                                .addChoice("\uD83C\uDFAE Xbox", Platform.XBL.getValue())
                                .addChoice("\uD83C\uDFAE PlayStation", Platform.PSN.getValue())
                                .addChoice("\uD83C\uDFA7 Steam", Platform.STEAM.getValue())
                        )
                        .addOption(OptionType.STRING, "nickname", "nickname on platform", true)

        ).queue();
    }
}
