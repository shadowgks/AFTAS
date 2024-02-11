package com.example.appgcm.repositories;

import com.example.appgcm.models.entity.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Page<Competition> findByLocationContaining(String location, Pageable pageable);
    Optional<Competition> findByCode(String code);
    Optional<Competition> findByDate(LocalDate date);
}
