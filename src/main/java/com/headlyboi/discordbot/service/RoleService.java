package com.headlyboi.discordbot.service;

import com.headlyboi.discordbot.enums.ApexRankRole;
import com.headlyboi.discordbot.handler.DiscordChannelHandler;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleService {

    public void buildRoles(GenericGuildEvent event) {
        Guild guild = event.getGuild();
        List<Role> guildRoles = event.getGuild().getRoles();

        TreeMap<String, ApexRankRole> rolesToCreate = new TreeMap<>(Arrays.stream(ApexRankRole.values())
                .collect(Collectors.toMap(ApexRankRole::getName, Function.identity())));
        for (Role role : guildRoles) {
            rolesToCreate.remove(role.getName());
        }

        for (ApexRankRole apexRankRole : rolesToCreate.values()) {
            String roleName = apexRankRole.getName();
            Color roleColor = apexRankRole.getRoleColor().getColor();
            createRole(guild, roleName, roleColor);
        }
    }

    private void createRole(Guild guild, String name, Color color) {
        guild.createRole()
                .setName(name)
                .setColor(color)
                .queue();
    }

}
