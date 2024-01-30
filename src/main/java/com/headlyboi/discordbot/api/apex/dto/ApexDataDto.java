package com.headlyboi.discordbot.api.apex.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApexDataDto {

    private ApexPlatformInfoDto platformInfo;

    private List<ApexSegmentDto> segments;

    public Optional<ApexSegmentDto> getOverview() {
        for (ApexSegmentDto apexSegment : segments) {
            if ("overview".equals(apexSegment.getType())) {
                return Optional.of(apexSegment);
            }
        }
        return Optional.empty();
    }
}
