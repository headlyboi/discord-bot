package com.headlyboi.discordbot.api.apex.dto.mozambique;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GlobalDto {

    private String name;
    private String tag;
    private String uid;
    private String avatar;
    private String platform;
    private int level;
    private int toNextLevelPercent;
    private int internalUpdateCount;
    private RankDTO rank;
}
