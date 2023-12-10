package com.example.appgcm.web.controllers;

import com.example.appgcm.dtos.competitionDto.CompetitionCreationReqDto;
import com.example.appgcm.services.CompetitionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/v1/competition")
@AllArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;

    @PostMapping
    public ResponseEntity<CompetitionCreationReqDto> create(@Valid @RequestBody CompetitionCreationReqDto competitionCreationReqDto){
        CompetitionCreationReqDto createCompetition = competitionService.save(competitionCreationReqDto);
        return ResponseEntity.ok(createCompetition);
    }
}
