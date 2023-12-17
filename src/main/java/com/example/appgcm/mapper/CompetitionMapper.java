package com.example.appgcm.mapper;

import com.example.appgcm.dtos.CompetitionDto.CompetitionDto;
import com.example.appgcm.models.entity.Competition;

public class CompetitionMapper {
    public static CompetitionDto mapToDto(Competition competition){
        return CompetitionDto.builder()
                .id(competition.getId())
                .code(competition.getCode())
                .date(competition.getDate())
                .startTime(competition.getStartTime())
                .amount(competition.getAmount())
                .endTime(competition.getEndTime())
                .location(competition.getLocation())
                .numberOfParticipants(competition.getNumberOfParticipants())
                .rankingList(competition.getRankingList())
                .build();
    }

//    public static Competition mapToEntity(CompetitionDto competitionDto){
//        return Competition.builder()
//                .date(competitionDto.date())
//                .startTime(competitionDto.startTime())
//                .amount(competitionDto.amount())
//                .endTime(competitionDto.endTime())
//                .location(competitionDto.location())
//                .numberOfParticipants(competitionDto.numberOfParticipants())
//                .build();
//    }
}
