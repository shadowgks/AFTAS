package com.example.appgcm.services.impls;

import com.example.appgcm.dtos.HuntingDto.Requests.HuntingReqDto;
import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.models.entity.Fish;
import com.example.appgcm.models.entity.Hunting;
import com.example.appgcm.models.entity.Member;
import com.example.appgcm.repositories.CompetitionRepository;
import com.example.appgcm.repositories.FishRepository;
import com.example.appgcm.repositories.HuntingRepository;
import com.example.appgcm.repositories.MemberRepository;
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

    @Override
    public Hunting huntingFish(HuntingReqDto huntingDto) {
        // Check if exists
        Optional<Fish> fish = Optional.ofNullable(fishRepository.findByName(huntingDto.fish().name())
                .orElseThrow(() -> new IllegalArgumentException("Sorry this fish not exists!")));
        Optional<Competition> competition = Optional.ofNullable(competitionRepository.findById(huntingDto.competitionId())
                .orElseThrow(() -> new IllegalArgumentException("Sorry this competition not exists!")));
        Optional<Member> member = Optional.ofNullable(memberRepository.findById(huntingDto.memberId())
                .orElseThrow(() -> new IllegalArgumentException("Sorry this member not exists!")));

        Optional<Hunting> findByCompetitionAndMemberAndFish = huntingRepository.findByCompetitionAndMemberAndFish(competition.get(), member.get(), fish.get());
        if (findByCompetitionAndMemberAndFish.isPresent()){

        }
        Hunting hunting = Hunting.builder()
                .fish(fish.get())
                .competition(competition.get())
                .member(member.get())
                .numberOfFish(1)
                .build();
        return huntingRepository.save(hunting);
    }
}
