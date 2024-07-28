package com.headlyboi.discordbot.service.reply;


import com.headlyboi.discordbot.api.apex.dto.mozambique.MozambiqueWrapperDto;
import com.headlyboi.discordbot.enums.ApexRank;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApexReplyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApexReplyService.class);

    private static final String PLATFORM = "Platform: ";
    private static final String NICKNAME = "Player name: ";
    private static final String RANK = "Rank: ";
    private static final String RANK_SCORE = "Rank score: ";
    private static final String NEXT_LINE = "\n";
    private static final String STYLE_START = "```java";
    private static final String STYLE_END = "```";

    public String replyStats(MozambiqueWrapperDto apexWrapperDataDto) {

        try {
            String nickName = apexWrapperDataDto.getGlobal().getName();
            String platform = apexWrapperDataDto.getGlobal().getPlatform();

            String rank = apexWrapperDataDto.getGlobal().getRank().getRankName();
            int rankScore = apexWrapperDataDto.getGlobal().getRank().getRankScore();
            return  STYLE_START + NEXT_LINE +
                    NICKNAME + nickName + NEXT_LINE +
                    PLATFORM + platform + NEXT_LINE + NEXT_LINE +
                    RANK + ApexRank.getApexRank(rank) + NEXT_LINE +
                    RANK_SCORE + rankScore + NEXT_LINE + NEXT_LINE +
                    STYLE_END + NEXT_LINE;
        } catch (NullPointerException e) {
            LOGGER.error("Error when reply stats", e);
            return "No data for player!";
        }
    }
}
