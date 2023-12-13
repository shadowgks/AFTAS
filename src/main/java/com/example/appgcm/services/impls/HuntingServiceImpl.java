package com.example.appgcm.services.impls;

import com.example.appgcm.dtos.HuntingDto.Requests.HuntingReqDto;
import com.example.appgcm.models.entity.*;
import com.example.appgcm.repositories.*;
import com.example.appgcm.services.HuntingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HuntingServiceImpl implements HuntingService {
    private final HuntingRepository huntingRepository;
    private final FishRepository fishRepository;
    private final MemberRepository memberRepository;
    private final CompetitionRepository competitionRepository;
    private final RankingRepository rankingRepository;

    @Override
    public Hunting huntingFish(HuntingReqDto huntingDto) {
        // Check if exists
        Optional<Fish> fish = Optional.ofNullable(fishRepository.findByName(huntingDto.fish().name())
                .orElseThrow(() -> new IllegalArgumentException("Sorry this fish not exists!")));
        Optional<Competition> competition = Optional.ofNullable(competitionRepository.findById(huntingDto.competitionId())
                .orElseThrow(() -> new IllegalArgumentException("Sorry this competition not exists!")));
        Optional<Member> member = Optional.ofNullable(memberRepository.findById(huntingDto.memberId())
                .orElseThrow(() -> new IllegalArgumentException("Sorry this member not exists!")));
        Optional<Ranking> ranking = Optional.ofNullable(rankingRepository.findByMemberAndCompetition(member.get(), competition.get())
                .orElseThrow(() -> new IllegalArgumentException("Sorry this member does not register yet in the competition!")));

        // Check average weight
        if (huntingDto.fish().weight() > fish.get().getAverageWeight()){
            throw new IllegalArgumentException("The weight of the hunted fish exceeds the average weight!");
        }

        // Check CompetitionAndMemberAndFish if exists | Update Hunting
        Optional<Hunting> findByCompetitionAndMemberAndFish = huntingRepository.findByCompetitionAndMemberAndFish(competition.get(), member.get(), fish.get());
        if (findByCompetitionAndMemberAndFish.isPresent()){
            Hunting huntingData = findByCompetitionAndMemberAndFish.get();
            Hunting updateHunting = Hunting.builder()
                    .id(huntingData.getId())
                    .fish(huntingData.getFish())
                    .numberOfFish(huntingData.getNumberOfFish() + 1)
                    .competition(huntingData.getCompetition())
                    .member(huntingData.getMember())
                    .build();
            return huntingRepository.save(updateHunting);
        }

        // Create Hunting
        Hunting createHunting = Hunting.builder()
                .fish(fish.get())
                .competition(competition.get())
                .member(member.get())
                .numberOfFish(1)
                .build();
        return huntingRepository.save(createHunting);
    }
}
