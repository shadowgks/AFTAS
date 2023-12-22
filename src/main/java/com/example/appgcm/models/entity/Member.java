package com.example.appgcm.models.entity;

import com.example.appgcm.models.enums.IdentityDocumentType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Type;


import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate accessionDate;
    private String nationality;
    private IdentityDocumentType identityDocumentType;
    @Column(unique = true)
    private String identityNumber;
    @OneToMany(mappedBy = "member")
    @JsonBackReference
    private List<Ranking> rankingList;
}
