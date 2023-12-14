package com.headlyboi.discordbot.api.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApexDataDto {

    @JsonProperty("segments")
    List<ApexSegmentDto> segments;

    @JsonGetter("segments")
    public List<ApexSegmentDto> getSegments() {
        return segments;
    }
}
