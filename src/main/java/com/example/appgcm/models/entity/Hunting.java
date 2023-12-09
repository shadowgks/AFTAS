package com.example.appgcm.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Hunting {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberOfFish;
    @ManyToOne
    private Competition competition;
    @ManyToOne
    private Fish fish;
    @ManyToOne
    private Member member;
}
