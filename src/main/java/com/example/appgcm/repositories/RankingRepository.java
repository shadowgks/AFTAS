package com.example.appgcm.repositories;

import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.models.entity.Member;
import com.example.appgcm.models.entity.Ranking;
import com.example.appgcm.models.entity.embedded.MemberCompetition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RankingRepository extends JpaRepository<Ranking, MemberCompetition> {
    Optional<Ranking> findByMemberAndCompetition(Member member, Competition competition);
}
