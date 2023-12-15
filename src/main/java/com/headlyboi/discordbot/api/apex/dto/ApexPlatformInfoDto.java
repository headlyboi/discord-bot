package com.headlyboi.discordbot.api.apex.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApexPlatformInfoDto {

    private String platformSlug;

    private String platformUserId;

    private String platformUserHandle;

    private String platformUserIdentifier;

}
