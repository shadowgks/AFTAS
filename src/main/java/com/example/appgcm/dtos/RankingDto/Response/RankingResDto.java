package com.example.appgcm.dtos.RankingDto.Response;

import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.models.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

@Builder
public record RankingResDto(
        Integer score,
        Integer rank,
        Member member,
        Competition competition
) {
}
