package com.example.appgcm.services;

import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.dtos.CompetitionDto.CompetitionDto;
import com.example.appgcm.dtos.RegisterMemberOnCompetitionDto;
import com.example.appgcm.models.entity.Ranking;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public interface CompetitionService {
    Page<Competition> findAllCompetitionPageble(String location, Integer numPage, Integer size);
    List<Competition> findAllCompetition();
    Competition findByDateCompetition(LocalDate date);
    Competition findByCodeCompetition(String code);
    Competition saveCompetition(CompetitionDto competition);
    void deleteCompetition(Long id);
    Competition updateCompetition(Long id, CompetitionDto competitionDto);
    Ranking registerMember(RegisterMemberOnCompetitionDto reqDto);
}
