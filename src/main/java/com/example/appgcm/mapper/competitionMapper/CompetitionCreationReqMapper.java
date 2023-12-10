package com.example.appgcm.mapper.competitionMapper;

import com.example.appgcm.dtos.competitionDto.CompetitionCreationReqDto;
import com.example.appgcm.models.entity.Competition;

public class CompetitionCreationReqMapper {
    public CompetitionCreationReqDto competitionCreationReqDto(Competition competition){
        return CompetitionCreationReqDto.builder()
                .date(competition.getDate())
                .startTime(competition.getStartTime())
                .amount(competition.getAmount())
                .endTime(competition.getEndTime())
                .location(competition.getLocation())
                .numberOfParticipants(competition.getNumberOfParticipants())
                .build();
    }
}
