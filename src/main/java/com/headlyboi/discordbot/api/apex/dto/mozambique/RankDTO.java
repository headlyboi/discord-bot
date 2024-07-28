package com.headlyboi.discordbot.api.apex.dto.mozambique;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RankDTO {

    private int rankScore;
    private String rankName;
    private int rankDiv;
    private int ladderPosPlatform;
    private String rankImg;
    private String rankedSeason;
    private double ALStopPercent;
    private int ALStopInt;
    private double ALStopPercentGlobal;
    private int ALStopIntGlobal;
    private boolean ALSFlag;
}
