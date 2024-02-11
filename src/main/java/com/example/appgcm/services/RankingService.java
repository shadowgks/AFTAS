package com.example.appgcm.services;

import com.example.appgcm.models.entity.Ranking;

import java.util.List;

public interface RankingService {
    List<Ranking> rankingListCompetition(String codeCompetition);
    public List<Ranking> calculeRanking(String codeCompetition);
}
