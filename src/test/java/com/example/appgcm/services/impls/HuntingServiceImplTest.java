package com.example.appgcm.services.impls;

import com.example.appgcm.dtos.HuntingDto.Requests.HuntingReqDto;
import com.example.appgcm.models.entity.*;
import com.example.appgcm.models.entity.embedded.MemberCompetition;
import com.example.appgcm.models.enums.IdentityDocumentType;
import com.example.appgcm.repositories.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class HuntingServiceImplTest {
    @Mock
    CompetitionRepository competitionRepository;
    @Mock
    RankingRepository rankingRepository;
    @Mock
    FishRepository fishRepository;
    @Mock
    MemberRepository memberRepository;
    @Mock
    HuntingRepository huntingRepository;

    @InjectMocks
    HuntingServiceImpl huntingService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Hunting a fish for the first time in a competition
    @Test
    public void test_hunting_first_time_add() {
        // Arrange
        HuntingReqDto huntingDto = HuntingReqDto.builder()
                .memberIdentity("1234567890")
                .competitionCode("COMP001")
                .fishName("Salmon")
                .weight(1.5)
                .build();

        Fish fish = Fish.builder()
                .id(1L)
                .name("Salmon")
                .averageWeight(1.0)
                .level(Level.builder().code(1).description("Beginner").points(10).build())
                .build();

        Competition competition = Competition.builder()
                .id(1L)
                .code("COMP001")
                .date(LocalDate.now())
                .startTime(LocalTime.of(9, 0))
                .endTime(LocalTime.of(12, 0))
                .numberOfParticipants(10)
                .location("Lake")
                .amount(100.0)
                .build();

        Member member = Member.builder()
                .id(1L)
                .firstName("ikram")
                .lastName("moumou")
                .accessionDate(LocalDate.now())
                .nationality("USA")
                .identityDocumentType(IdentityDocumentType.PASSPORT)
                .identityNumber("1234567890")
                .rankingList(new ArrayList<>())
                .build();

        Hunting hunting = Hunting.builder()
                .id(1L)
                .numberOfFish(1)
                .competition(competition)
                .fish(fish)
                .member(member)
                .build();

        Ranking ranking = Ranking.builder()
                .id(MemberCompetition.builder().memberID(1L).competitionID(1L).build())
                .score(10)
                .rankk(1)
                .member(member)
                .competition(competition)
                .build();

        when(fishRepository.findByName("Salmon")).thenReturn(Optional.of(fish));
        when(competitionRepository.findByCode("COMP001")).thenReturn(Optional.of(competition));
        when(memberRepository.findByIdentityNumber("1234567890")).thenReturn(Optional.of(member));
        when(rankingRepository.findByMemberAndCompetition(member, competition)).thenReturn(Optional.of(ranking));
        when(huntingRepository.findByCompetitionAndMemberAndFish(competition, member, fish)).thenReturn(Optional.empty());
        when(huntingRepository.save(any(Hunting.class))).thenReturn(hunting);
        when(rankingRepository.save(any(Ranking.class))).thenReturn(ranking);

        // Act
        Hunting result = huntingService.sumHuntingFish(huntingDto);

        // Assert
        assertNotNull(result);
        assertEquals(competition, result.getCompetition());
        assertEquals(fish, result.getFish());
        assertEquals(member, result.getMember());
    }

    @Test
    public void test_hunting_second_time_update() {
        // Arrange
        HuntingReqDto huntingDto = HuntingReqDto.builder()
                .memberIdentity("1234567890")
                .competitionCode("COMP001")
                .fishName("Salmon")
                .weight(1.5)
                .build();

        Fish fish = Fish.builder()
                .id(1L)
                .name("Salmon")
                .averageWeight(1.0)
                .level(Level.builder().code(1).description("Beginner").points(10).build())
                .build();

        Competition competition = Competition.builder()
                .id(1L)
                .code("COMP001")
                .date(LocalDate.now())
                .startTime(LocalTime.of(9, 0))
                .endTime(LocalTime.of(12, 0))
                .numberOfParticipants(10)
                .location("Lake")
                .amount(100.0)
                .build();

        Member member = Member.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .accessionDate(LocalDate.now())
                .nationality("USA")
                .identityDocumentType(IdentityDocumentType.PASSPORT)
                .identityNumber("1234567890")
                .rankingList(new ArrayList<>())
                .build();

        Hunting existingHunting = Hunting.builder()
                .id(1L)
                .numberOfFish(1)
                .competition(competition)
                .fish(fish)
                .member(member)
                .build();

        Ranking ranking = Ranking.builder()
                .id(MemberCompetition.builder().memberID(1L).competitionID(1L).build())
                .score(10)
                .rankk(1)
                .member(member)
                .competition(competition)
                .build();

        when(fishRepository.findByName("Salmon")).thenReturn(Optional.of(fish));
        when(competitionRepository.findByCode("COMP001")).thenReturn(Optional.of(competition));
        when(memberRepository.findByIdentityNumber("1234567890")).thenReturn(Optional.of(member));
        when(rankingRepository.findByMemberAndCompetition(member, competition)).thenReturn(Optional.of(ranking));
        when(huntingRepository.findByCompetitionAndMemberAndFish(competition, member, fish)).thenReturn(Optional.of(existingHunting));
        when(huntingRepository.save(any(Hunting.class))).thenReturn(existingHunting);
        when(rankingRepository.save(any(Ranking.class))).thenReturn(ranking);

        HuntingServiceImpl huntingService = new HuntingServiceImpl(huntingRepository, fishRepository, memberRepository, competitionRepository, rankingRepository);

        // Act
        Hunting result = huntingService.sumHuntingFish(huntingDto);

        // Assert
        assertNotNull(result);
        assertEquals(competition, result.getCompetition());
        assertEquals(fish, result.getFish());
        assertEquals(member, result.getMember());
    }

}