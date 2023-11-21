package com.headlyboi.discordbot.service;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleService {

    public void createRoles(GenericGuildEvent event) {
        Guild guild = event.getGuild();
        List<Role> roles = event.getGuild().getRoles();

    }
}
