package com.example.appgcm.dtos.RankingDto.Response;

import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.models.entity.AppUser;
import lombok.Builder;

@Builder
public record RankingResDto(
        Integer score,
        Integer rank,
        AppUser user,
        Competition competition
) {
}
