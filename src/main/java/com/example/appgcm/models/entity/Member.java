package com.example.appgcm.models.entity;

import com.example.appgcm.models.enums.IdentityDocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Type;


import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
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
    private String identityNumber;
    @OneToMany(mappedBy = "member")
    private List<Ranking> rankings;
}
