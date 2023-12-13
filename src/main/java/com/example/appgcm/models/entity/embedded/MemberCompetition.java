package com.example.appgcm.models.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberCompetition {
    @Column(name = "member_id")
    private Long memberID;
    @Column(name = "competition_id")
    private Long competitionID;
}
