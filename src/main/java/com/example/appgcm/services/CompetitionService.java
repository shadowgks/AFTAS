package com.example.appgcm.services;

import com.example.appgcm.dtos.CompetitionDto;
import com.example.appgcm.models.entity.Competition;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CompetitionService {
    List<Competition> findAllCompetition();
    Competition findByDateCompetition(LocalDate date);
    Competition findByCodeCompetition(String code);
    Competition saveCompetition(CompetitionDto competition);
    void deleteCompetition(Long id);
    Competition updateCompetition(Long id, CompetitionDto competitionDto);
}
