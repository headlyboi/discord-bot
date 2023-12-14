package com.headlyboi.discordbot.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApexStatsDto {

    private ApexKillsDto kills;

    private ApexDamageDto damage;

    private ApexRankScoreDto rankScore;
}
