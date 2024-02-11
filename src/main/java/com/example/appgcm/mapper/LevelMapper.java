package com.example.appgcm.mapper;

import com.example.appgcm.models.entity.Level;
import com.example.appgcm.dtos.LevelDto;

public class LevelMapper {
    public static LevelDto mapToDto(Level level){
        return LevelDto.builder()
                .code(level.getCode())
                .points(level.getPoints())
                .description(level.getDescription())
                .build();
    }
}
