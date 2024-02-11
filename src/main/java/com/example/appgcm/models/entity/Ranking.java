package com.example.appgcm.models.entity;

import com.example.appgcm.models.entity.embedded.MemberCompetition;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Ranking {
    @EmbeddedId
    private MemberCompetition id;
    private Integer score;
    private Integer rankk;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @MapsId("userID")
    private AppUser user;
    @ManyToOne
    @JoinColumn(name = "competition_id")
    @MapsId("competitionID")
    private Competition competition;

}
