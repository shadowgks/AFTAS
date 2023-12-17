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
    @JoinColumn(name = "member_id")
    @MapsId("memberID")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "competition_id")
    @MapsId("competitionID")
    private Competition competition;

}
