package com.example.appgcm.repositories;

import com.example.appgcm.models.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LevelRepository extends JpaRepository<Level, Long> {
    Optional<Level> getLevelByCode(Integer code);
}
