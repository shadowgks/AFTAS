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
public class Level {
    @Id
    @Column(unique = true)
    private Integer code;
    private String description;
    @Column(nullable = false, unique = true)
    private Integer points;
}
