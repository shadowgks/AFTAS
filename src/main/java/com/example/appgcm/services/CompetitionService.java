package com.example.appgcm.services;

import com.example.appgcm.dtos.competitionDto.CompetitionCreationReqDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CompetitionService {
    Optional<List<CompetitionCreationReqDto>> findAll();
    Optional<CompetitionCreationReqDto> findByDate();
    CompetitionCreationReqDto save(CompetitionCreationReqDto competitionCreationReqDto);
}
