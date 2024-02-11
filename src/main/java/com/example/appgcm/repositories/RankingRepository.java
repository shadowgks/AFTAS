package com.example.appgcm.repositories;

import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.models.entity.AppUser;
import com.example.appgcm.models.entity.Ranking;
import com.example.appgcm.models.entity.embedded.MemberCompetition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, MemberCompetition> {
    Optional<Ranking> findByUserAndCompetition(AppUser user, Competition competition);
    Integer countByCompetition(Competition competition);
    List<Ranking> findAllByCompetitionOrderByScoreDesc(Competition competition);

}
