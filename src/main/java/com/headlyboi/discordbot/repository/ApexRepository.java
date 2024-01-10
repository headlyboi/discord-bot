package com.headlyboi.discordbot.repository;

import com.headlyboi.discordbot.api.apex.dto.ApexWrapperDataDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApexRepository {

    private final Map<Long, ApexWrapperDataDto> dataMap = new HashMap<>();

    public void save(final Long id, final ApexWrapperDataDto dataDto) {
        dataMap.put(id, dataDto);
    }

    public ApexWrapperDataDto findById(final Long id) {
        return dataMap.get(id);
    }

}
