package com.example.appgcm.dtos.FishDto.Response;

import com.example.appgcm.models.entity.Level;
import lombok.Builder;

@Builder
public record FishResDto(
        Long id,
        String name,
        Double averageWeight,
        Level level
) {

}
