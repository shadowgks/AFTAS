package com.example.appgcm.repositories;

import com.example.appgcm.models.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    Optional<Level> findLevelByCode(Integer code);
    Optional<Level> findLevelByPoints(Integer points);
}
