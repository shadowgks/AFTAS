package com.example.appgcm.repositories;

import com.example.appgcm.models.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Optional<Competition> findByCode(String code);
    Optional<Competition> findByDate(LocalDate date);
}
