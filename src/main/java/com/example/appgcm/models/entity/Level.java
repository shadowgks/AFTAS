package com.example.appgcm.models.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Level {
    @Id
    @Column(unique = true)
    private Integer code;
    private String description;
    @Column(nullable = false, unique = true)
    private Integer points;
}
