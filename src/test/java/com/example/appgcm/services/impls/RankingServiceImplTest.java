package com.example.appgcm.services.impls;

import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.models.entity.Member;
import com.example.appgcm.models.entity.Ranking;
import com.example.appgcm.models.enums.IdentityDocumentType;
import com.example.appgcm.repositories.CompetitionRepository;
import com.example.appgcm.repositories.RankingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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
    List<Member> members;
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
        assertThrows(IllegalArgumentException.class,()->rankingServiceImpl.calculRanking(codeCompetition));
    }

}