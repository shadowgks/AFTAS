package com.example.appgcm.models.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class MemberCompetition implements Serializable {
    @Column(name = "member_id")
    private Long memberID;
    @Column(name = "competition_id")
    private Long competitionID;
}
