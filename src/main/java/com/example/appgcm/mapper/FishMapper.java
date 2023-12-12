package com.example.appgcm.mapper;

import com.example.appgcm.dtos.FishDto;
import com.example.appgcm.models.entity.Fish;

public class FishMapper {
    public static FishDto mapToDto(Fish fish){
        return FishDto.builder()
                .id(fish.getId())
                .name(fish.getName())
                .averageWeight(fish.getAverageWeight())
                .level(fish.getLevel().getCode())
                .build();
    }
}
