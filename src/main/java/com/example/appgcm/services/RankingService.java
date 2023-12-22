package com.example.appgcm.services;

import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.models.entity.Hunting;
import com.example.appgcm.models.entity.Ranking;

import java.util.List;
import java.util.Map;

public interface RankingService {
    List<Ranking> RankingListCompetition(String codeCompetition);
    public List<Ranking> calculRanking(String codeCompetition);
}
