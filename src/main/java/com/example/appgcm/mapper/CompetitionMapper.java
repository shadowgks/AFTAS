package com.example.appgcm.mapper;

import com.example.appgcm.dtos.CompetitionDto.CompetitionReqDto;
import com.example.appgcm.dtos.CompetitionDto.CompetitionResDto;
import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.dtos.CompetitionDto.CompetitionDto;

public class CompetitionMapper {
    public static CompetitionResDto mapToDto(Competition competition){
        return CompetitionResDto.builder()
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

    public static Competition mapToEntity(CompetitionReqDto reqDto){
        return Competition.builder()
                .date(reqDto.date())
                .startTime(reqDto.startTime())
                .amount(reqDto.amount())
                .endTime(reqDto.endTime())
                .location(reqDto.location())
                .numberOfParticipants(reqDto.numberOfParticipants())
                .build();
    }
}
