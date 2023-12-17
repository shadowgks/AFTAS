package com.example.appgcm.services;

import com.example.appgcm.dtos.CompetitionDto.CompetitionDto;
import com.example.appgcm.dtos.RegisterMemberOnCompetitionDto;
import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.models.entity.Ranking;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface CompetitionService {
    Page<Competition> findAllCompetition(String location, Integer numPage, Integer size);
    Competition findByDateCompetition(LocalDate date);
    Competition findByCodeCompetition(String code);
    Competition saveCompetition(CompetitionDto competition);
    void deleteCompetition(Long id);
    Competition updateCompetition(Long id, CompetitionDto competitionDto);
    Ranking registerMember(RegisterMemberOnCompetitionDto reqDto);
}
