package com.example.appgcm.services.impls;

import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.repositories.CompetitionRepository;
import com.example.appgcm.repositories.RankingRepository;
import com.example.appgcm.models.entity.AppUser;
import com.example.appgcm.models.entity.Ranking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RankingServiceImplTest {

    @Mock
    CompetitionRepository competitionRepository;
    @Mock
    RankingRepository rankingRepository;

    @InjectMocks
    RankingServiceImpl rankingServiceImpl;

    Ranking ranking;
    List<AppUser> user;
    Competition competition;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void calculRanking_ReturnIllegalArgumentException() {
        String codeCompetition = "Test";
        when(competitionRepository.findByCode(codeCompetition))
                .thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class,()->rankingServiceImpl.calculeRanking(codeCompetition));
    }

}