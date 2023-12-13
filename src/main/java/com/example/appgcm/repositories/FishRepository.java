package com.example.appgcm.repositories;

import com.example.appgcm.models.entity.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FishRepository extends JpaRepository<Fish, Long> {
    Optional<Fish> findByName(String name);
}
