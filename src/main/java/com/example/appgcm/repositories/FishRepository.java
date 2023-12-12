package com.example.appgcm.repositories;

import com.example.appgcm.models.entity.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository extends JpaRepository<Fish, Long> {
}
