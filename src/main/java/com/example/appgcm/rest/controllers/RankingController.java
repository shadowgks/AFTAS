package com.example.appgcm.rest.controllers;

import com.example.appgcm.mapper.RankingMapper;
import com.example.appgcm.utils._Response;
import com.example.appgcm.dtos.RankingDto.Response.RankingResDto;
import com.example.appgcm.models.entity.Ranking;
import com.example.appgcm.services.RankingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/ranking")
@PreAuthorize("hasAnyAuthority('ROLE:MANAGER', 'ROLE:JURY', 'ROLE:ADHERENT')")
@RequiredArgsConstructor
public class RankingController {
    private final RankingService rankingService;

    @GetMapping("{codeCompetition}")
    public ResponseEntity<_Response<List<RankingResDto>>> listRankingByCompetition(@Valid @PathVariable("codeCompetition") String codeCompetition){
        _Response<List<RankingResDto>> response = new _Response<>();

        //List Ranking
        List<Ranking> rankingList = rankingService.rankingListCompetition(codeCompetition);

        //Response
        response.setResult(rankingList
                .stream()
                .map(RankingMapper::mapToDto)
                .collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }
}
