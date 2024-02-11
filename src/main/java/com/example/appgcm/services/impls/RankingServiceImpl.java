package com.example.appgcm.services.impls;

import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.repositories.CompetitionRepository;
import com.example.appgcm.models.entity.Ranking;
import com.example.appgcm.repositories.RankingRepository;
import com.example.appgcm.services.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {
    private final RankingRepository rankingRepository;
    private final CompetitionRepository competitionRepository;
    @Override
    public List<Ranking> rankingListCompetition(String codeCompetition) {
       return rankingRepository.saveAll(calculeRanking(codeCompetition));
    }

    @Override
    public List<Ranking> calculeRanking(String codeCompetition){
        Optional<Competition> competition1 = Optional.of(competitionRepository.findByCode(codeCompetition))
                .orElseThrow(() -> new IllegalArgumentException("Not found this Competition!"));

        // Get ranking competition by order Desc
        List<Ranking> rankingList = rankingRepository.findAllByCompetitionOrderByScoreDesc(competition1.get());

        // update rank member
        AtomicReference<Integer> index = new AtomicReference<>(1);
        return rankingList.stream()
                .map(rc -> {
                    rc.setRankk(index.getAndSet(index.get() + 1));
                    return rc;
                }).collect(Collectors.toList());
    }
}
