package com.example.appgcm.rest.controllers;

import com.example.appgcm.dtos.CompetitionDto.CompetitionDto;
import com.example.appgcm.dtos.RankingDto.Response.RankingResDto;
import com.example.appgcm.dtos.RegisterMemberOnCompetitionDto;
import com.example.appgcm.mapper.CompetitionMapper;
import com.example.appgcm.mapper.RankingMapper;
import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.models.entity.Ranking;
import com.example.appgcm.services.CompetitionService;
import com.example.appgcm.utils.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/competition")
@RequiredArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;

    @PostMapping("/create")
    public ResponseEntity<Response<CompetitionDto>> createCompetition(@Valid @RequestBody CompetitionDto reqDto){
        Response<CompetitionDto> competitionDtoResponse = new Response<>();
        Competition createCompetition = competitionService.saveCompetition(reqDto);
        competitionDtoResponse.setResult(CompetitionMapper.mapToDto(createCompetition));
        competitionDtoResponse.setMessage("Created Competition Successfully");
        return new ResponseEntity<>(competitionDtoResponse, HttpStatus.CREATED);
    }

    @PostMapping("/register_member")
    public ResponseEntity<Response<RankingResDto>> registerMember(@Valid @RequestBody RegisterMemberOnCompetitionDto reqDto){
        Response<RankingResDto> response = new Response<>();
        Ranking ranking = competitionService.registerMember(reqDto);
        response.setResult(RankingMapper.mapToDto(ranking));
        response.setMessage("Registered member in competition successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Response<Map<String ,Page<Competition>>>> getAllCompetition(@RequestParam Optional<String> location,
                                                                            @RequestParam Optional<Integer> numPage,
                                                                            @RequestParam Optional<Integer> size){
        Response<Map<String, Page<Competition>>> listResponse = new Response<>();
        Map<String, Page<Competition>> stringListMap = new HashMap<>();
        Page<Competition> competitionList = competitionService.findAllCompetition(
                location.orElse(""),
                numPage.orElse(0),
                size.orElse(10));
        stringListMap.put("page", competitionList);
        listResponse.setResult(stringListMap);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/find-by-code/{code}")
    public ResponseEntity<Response<CompetitionDto>> getCompetitionByCode(@Valid @PathVariable("code") String code){
        Response<CompetitionDto> response = new Response<>();
        Competition competition = competitionService.findByCodeCompetition(code);
        response.setResult(CompetitionMapper.mapToDto(competition));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find-by-date/{date}")
    public ResponseEntity<Response<CompetitionDto>> getCompetitionByCode(@Valid @PathVariable("date") LocalDate date){
        Response<CompetitionDto> response = new Response<>();
        Competition competition = competitionService.findByDateCompetition(date);
        response.setResult(CompetitionMapper.mapToDto(competition));
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Response<CompetitionDto>> updateCompetitionByCode(@Valid @PathVariable("id") Long id, @RequestBody CompetitionDto reqDto){
        Response<CompetitionDto> response = new Response<>();
        Competition competition = competitionService.updateCompetition(id, reqDto);
        response.setResult(CompetitionMapper.mapToDto(competition));
        response.setMessage("Update Competition Successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<CompetitionDto>> deleteCompetitionById(@Valid @PathVariable("id") Long id){
        Response<CompetitionDto> response = new Response<>();
        competitionService.deleteCompetition(id);
        response.setMessage("Deleted Competition Successfully");
        return ResponseEntity.ok(response);
    }
}
