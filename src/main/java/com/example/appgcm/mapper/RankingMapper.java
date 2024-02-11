package com.example.appgcm.mapper;

import com.example.appgcm.dtos.RankingDto.Response.RankingResDto;
import com.example.appgcm.models.entity.Ranking;
import lombok.Builder;

@Builder
public class RankingMapper {
    public static RankingResDto mapToDto(Ranking ranking){
        return RankingResDto.builder()
                .rank(ranking.getRankk())
                .score(ranking.getScore())
                .competition(ranking.getCompetition())
                .user(ranking.getUser())
                .build();
    }
}
