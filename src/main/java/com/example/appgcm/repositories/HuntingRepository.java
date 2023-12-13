package com.example.appgcm.repositories;

import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.models.entity.Fish;
import com.example.appgcm.models.entity.Hunting;
import com.example.appgcm.models.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HuntingRepository extends JpaRepository<Hunting, Long> {
    Optional<Hunting> findByCompetitionAndMemberAndFish(Competition competition, Member member, Fish fish);
}
