package com.headlyboi.discordbot.service.reply;


import com.headlyboi.discordbot.api.apex.dto.ApexSegmentDto;
import com.headlyboi.discordbot.api.apex.dto.ApexWrapperDataDto;
import com.headlyboi.discordbot.enums.ApexRank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApexReplyService implements IBotReplyService {

    private static final String NICKNAME = "Player name: ";
    private static final String RANK = "Rank: ";
    private static final String RANK_SCORE = "Rank score: ";
    private static final String ARENA_RANK = "Arena Rank: ";
    private static final String ARENA_RANK_SCORE = "Arena Rank score: ";
    private static final String KILLS = "Kills: ";
    private static final String DAMAGE = "Damage: ";
    private static final String NEXT_LINE = "\n";
    private static final String STYLE_START = "```php";
    private static final String STYLE_END = "```";

    @Override
    public String replyStats(ApexWrapperDataDto apexWrapperDataDto) {
        Optional<ApexSegmentDto> overview = apexWrapperDataDto.getData().getOverview();

        if (overview.isPresent()) {
            ApexSegmentDto apexSegment = overview.get();

            String rank = apexSegment.getStats().getRankScore().getMetadata().getRank();
            String arenaRank = apexSegment.getStats().getArenaRankScore().getMetadata().getRank();

            return STYLE_START + NEXT_LINE +
                    RANK + ApexRank.getApexRank(rank) + NEXT_LINE +
                    RANK_SCORE + apexSegment.getStats().getRankScore().getValue() + NEXT_LINE + NEXT_LINE +
                    ARENA_RANK + ApexRank.getApexRank(arenaRank) + NEXT_LINE +
                    ARENA_RANK_SCORE + apexSegment.getStats().getArenaRankScore().getValue() + NEXT_LINE + NEXT_LINE +
                    KILLS + apexSegment.getStats().getKills().getValue() + NEXT_LINE +
                    DAMAGE + apexSegment.getStats().getDamage().getValue() + NEXT_LINE +
                    STYLE_END;
        }

        return "No data for player!";
    }
}
