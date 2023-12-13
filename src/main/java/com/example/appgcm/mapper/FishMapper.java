package com.example.appgcm.mapper;

import com.example.appgcm.dtos.FishDto.Response.FishResDto;
import com.example.appgcm.models.entity.Fish;

public class FishMapper {
    public static FishResDto mapToDto(Fish fish){
        return FishResDto.builder()
                .id(fish.getId())
                .name(fish.getName())
                .averageWeight(fish.getAverageWeight())
                .level(fish.getLevel())
                .build();
    }
}
