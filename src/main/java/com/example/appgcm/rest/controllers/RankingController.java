package com.example.appgcm.rest.controllers;

import com.example.appgcm.dtos.HuntingDto.Response.HuntingResDto;
import com.example.appgcm.dtos.RankingDto.Requests.RankingReqDto;
import com.example.appgcm.dtos.RankingDto.Response.RankingResDto;
import com.example.appgcm.mapper.RankingMapper;
import com.example.appgcm.models.entity.Ranking;
import com.example.appgcm.services.RankingService;
import com.example.appgcm.utils.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/ranking")
@RequiredArgsConstructor
public class RankingController {
    private final RankingService rankingService;

    @GetMapping("{codeCompetition}")
    public ResponseEntity<Response<List<RankingResDto>>> listRankingByCompetition(@Valid @PathVariable("codeCompetition") String codeCompetition){
        Response<List<RankingResDto>> response = new Response<>();

        //List Ranking
        List<Ranking> rankingList = rankingService.RankingListCompetition(codeCompetition);

        //Response
        response.setResult(rankingList
                .stream()
                .map(RankingMapper::mapToDto)
                .collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }
}
