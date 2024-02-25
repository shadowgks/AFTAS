package com.example.appgcm.dtos.CompetitionDto;

import com.example.appgcm.models.entity.Ranking;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Builder
public record CompetitionResDto(
        Long id,
        String code,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        Integer numberOfParticipants,
        String location,
        Double amount,
        List<Ranking> rankingList
) {
}
