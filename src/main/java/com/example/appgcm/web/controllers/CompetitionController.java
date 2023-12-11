package com.example.appgcm.web.controllers;

import com.example.appgcm.dtos.CompetitionDto;
import com.example.appgcm.mapper.CompetitionMapper;
import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.services.CompetitionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/competition")
@RequiredArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;

    @PostMapping("/create")
    public ResponseEntity<Competition> createCompetition(@Valid @RequestBody CompetitionDto reqDto){
        Competition createCompetition = competitionService.saveCompetition(reqDto);
        return ResponseEntity.ok().body(createCompetition);
    }

    @GetMapping
    public ResponseEntity<Optional<List<CompetitionDto>>> getAllCompetition(){
        Optional<List<CompetitionDto>> competitionDtos = competitionService.findAllCompetition();
        return ResponseEntity.ok(competitionDtos);
    }

    @GetMapping("/find-by-code/{code}")
    public ResponseEntity<Optional<CompetitionDto>> getCompetitionByCode(@Valid @PathVariable("code") String code){
        Optional<CompetitionDto> competitionDto = competitionService.findByCodeCompetition(code);
        return ResponseEntity.ok(competitionDto);
    }

    @GetMapping("/find-by-date/{date}")
    public ResponseEntity<Optional<CompetitionDto>> getCompetitionByCode(@Valid @PathVariable("date") LocalDate date){
        Optional<CompetitionDto> competitionDto = competitionService.findByDateCompetition(date);
        return ResponseEntity.ok(competitionDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Optional<CompetitionDto>> updateCompetitionByCode(@Valid @PathVariable("id") Long id, @RequestBody CompetitionDto reqDto){
        competitionService.updateCompetition(id, reqDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CompetitionDto> deleteCompetitionByCode(@Valid @PathVariable("id") Long id){
        competitionService.deleteCompetition(id);
        return ResponseEntity.noContent().build();
    }
}
