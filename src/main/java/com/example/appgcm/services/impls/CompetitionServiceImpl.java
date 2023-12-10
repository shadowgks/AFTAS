package com.example.appgcm.services.impls;

import com.example.appgcm.dtos.competitionDto.CompetitionCreationReqDto;
import com.example.appgcm.services.CompetitionService;

import java.util.List;
import java.util.Optional;

public class CompetitionServiceImpl implements CompetitionService {
    @Override
    public Optional<List<CompetitionCreationReqDto>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<CompetitionCreationReqDto> findByDate() {
        return Optional.empty();
    }

    @Override
    public CompetitionCreationReqDto save() {
        return null;
    }
}
