package com.example.appgcm.models.entity;

import com.example.appgcm.models.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private RoleType name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles-permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Persmission> permissions;
}
