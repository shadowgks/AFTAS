package com.example.appgcm.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    private AppUser user;
}
