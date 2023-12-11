package com.example.appgcm.services;

import com.example.appgcm.dtos.CompetitionDto;
import com.example.appgcm.models.entity.Competition;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CompetitionService {
    Optional<List<CompetitionDto>> findAllCompetition();
    Optional<CompetitionDto> findByDateCompetition(LocalDate date);
    Optional<CompetitionDto> findByCodeCompetition(String code);
    Competition saveCompetition(CompetitionDto competition);
    void deleteCompetition(String code);
    void updateCompetition(String code, CompetitionDto competitionDto);
}
