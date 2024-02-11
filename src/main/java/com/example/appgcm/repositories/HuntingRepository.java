package com.example.appgcm.repositories;

import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.models.entity.Fish;
import com.example.appgcm.models.entity.Hunting;
import com.example.appgcm.models.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Long> {
    Optional<Hunting> findByCompetitionAndUserAndFish(Competition competition, AppUser user, Fish fish);
}
