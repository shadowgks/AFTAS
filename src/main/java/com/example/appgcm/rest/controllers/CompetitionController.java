package com.example.appgcm.rest.controllers;

import com.example.appgcm.dtos.CompetitionDto.CompetitionReqDto;
import com.example.appgcm.dtos.CompetitionDto.CompetitionResDto;
import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.dtos.RankingDto.Response.RankingResDto;
import com.example.appgcm.dtos.RegisterMemberOnCompetitionDto;
import com.example.appgcm.mapper.CompetitionMapper;
import com.example.appgcm.mapper.RankingMapper;
import com.example.appgcm.models.entity.Ranking;
import com.example.appgcm.services.CompetitionService;
import com.example.appgcm.utils._Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/competition")
@RequiredArgsConstructor
public class CompetitionController {
    private final CompetitionService competitionService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ROLE:MANAGER', 'ROLE:JURY')")
    public ResponseEntity<_Response<CompetitionResDto>> createCompetition(@Valid @RequestBody CompetitionReqDto reqDto){
        _Response<CompetitionResDto> competitionDtoResponse = new _Response<>();
        Competition createCompetition = competitionService.saveCompetition(reqDto);
        competitionDtoResponse.setResult(CompetitionMapper.mapToDto(createCompetition));
        competitionDtoResponse.setMessage("Created Competition Successfully");
        return new ResponseEntity<>(competitionDtoResponse, HttpStatus.CREATED);
    }


    @PostMapping("/register_member")
    @PreAuthorize("hasAnyAuthority('ROLE:MANAGER', 'ROLE:JURY')")
    public ResponseEntity<_Response<RankingResDto>> registerMember(@Valid @RequestBody RegisterMemberOnCompetitionDto reqDto){
        _Response<RankingResDto> response = new _Response<>();
        Ranking ranking = competitionService.registerMember(reqDto);
        response.setResult(RankingMapper.mapToDto(ranking));
        response.setMessage("Registered member in competition successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE:MANAGER', 'ROLE:JURY', 'ROLE:ADHERENT')")
    public ResponseEntity<_Response<Map<String ,Page<Competition>>>> getAllCompetitionPageble(@RequestParam Optional<String> location,
                                                                                              @RequestParam Optional<Integer> numPage,
                                                                                              @RequestParam Optional<Integer> size){
        _Response<Map<String, Page<Competition>>> listResponse = new _Response<>();
        Map<String, Page<Competition>> stringListMap = new HashMap<>();
        Page<Competition> competitionList = competitionService.findAllCompetitionPageble(
                location.orElse(""),
                numPage.orElse(0),
                size.orElse(200));
        stringListMap.put("page", competitionList);
        listResponse.setResult(stringListMap);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ROLE:MANAGER', 'ROLE:JURY', 'ROLE:ADHERENT')")
    public ResponseEntity<_Response<List<CompetitionResDto>>> getAllCompetition(){
        _Response<List<CompetitionResDto>> responseList = new _Response<>();
        List<Competition> competitionList = competitionService.findAllCompetition();
        responseList.setResult(competitionList
                .stream().map(CompetitionMapper::mapToDto)
                .toList());
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/find-by-code/{code}")
    @PreAuthorize("hasAnyAuthority('ROLE:MANAGER', 'ROLE:JURY', 'ROLE:ADHERENT')")
    public ResponseEntity<_Response<CompetitionResDto>> getCompetitionByCode(@Valid @PathVariable("code") String code){
        _Response<CompetitionResDto> response = new _Response<>();
        Competition competition = competitionService.findByCodeCompetition(code);
        response.setResult(CompetitionMapper.mapToDto(competition));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find-by-date/{date}")
    @PreAuthorize("hasAnyAuthority('ROLE:MANAGER', 'ROLE:JURY', 'ROLE:ADHERENT')")
    public ResponseEntity<_Response<CompetitionResDto>> getCompetitionByCode(@Valid @PathVariable("date") LocalDate date){
        _Response<CompetitionResDto> response = new _Response<>();
        Competition competition = competitionService.findByDateCompetition(date);
        response.setResult(CompetitionMapper.mapToDto(competition));
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE:MANAGER', 'ROLE:JURY')")
    public ResponseEntity<_Response<CompetitionResDto>> updateCompetitionByCode(@Valid @PathVariable("id") Long id, @RequestBody CompetitionReqDto reqDto){
        _Response<CompetitionResDto> response = new _Response<>();
        Competition competition = competitionService.updateCompetition(id, reqDto);
        response.setResult(CompetitionMapper.mapToDto(competition));
        response.setMessage("Update Competition Successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE:MANAGER', 'ROLE:JURY')")
    public ResponseEntity<_Response<CompetitionResDto>> deleteCompetitionById(@Valid @PathVariable("id") Long id){
        _Response<CompetitionResDto> response = new _Response<>();
        competitionService.deleteCompetition(id);
        response.setMessage("Deleted Competition Successfully");
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/filter")
//    public ResponseEntity<Response<CompetitionDto>> filterCompetition(){
//        Response<CompetitionDto> response = new Response<>();
//
//        return ResponseEntity.ok(response);
//    }
}
