package com.headlyboi.discordbot.service;

import com.headlyboi.discordbot.enums.ApexRank;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleBuilderService {

    public void buildRoles(GenericGuildEvent event) {
        Guild guild = event.getGuild();
        List<Role> guildRoles = event.getGuild().getRoles();

        TreeMap<String, ApexRank> rolesToCreate = new TreeMap<>(Arrays.stream(ApexRank.values())
                .collect(Collectors.toMap(ApexRank::getValue, Function.identity())));
        for (Role role : guildRoles) {
            rolesToCreate.remove(role.getName());
        }

        for (ApexRank apexRank : rolesToCreate.values()) {
            String roleName = apexRank.getValue();
            Color roleColor = apexRank.getApexColor().getColor();
            createRole(guild, roleName, roleColor);
        }
    }

    public void addRoleToUser(SlashCommandInteractionEvent event, ApexRank rank) {
        List<Role> guildRoles = Objects.requireNonNull(event.getGuild()).getRoles();

        TreeMap<String, ApexRank> rolesToCreate = new TreeMap<>(Arrays.stream(ApexRank.values())
                .collect(Collectors.toMap(ApexRank::getValue, Function.identity())));
        for (Role role : Objects.requireNonNull(event.getMember()).getRoles()) {
            if (rolesToCreate.containsKey(role.getName())) {
                event.getGuild().removeRoleFromMember(Objects.requireNonNull(event.getMember()), role).queue();
            }
        }

        for (Role role : guildRoles) {
            if (rank.getValue().equals(role.getName())) {
                event.getGuild().addRoleToMember(Objects.requireNonNull(event.getMember()), role).queue();
            }
        }

    }

    private void createRole(Guild guild, String name, Color color) {
        guild.createRole()
                .setName(name)
                .setColor(color)
                .queue();
    }

}
