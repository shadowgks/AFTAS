package com.example.appgcm.models.entity;

import com.example.appgcm.models.enums.IdentityDocumentType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "_user")
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String userName;
    private String password;
    private LocalDate accessionDate;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;
    @Column(unique = true)
    private String identityNumber;
    private String isWorking;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Ranking> rankingList;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Competition> competitionList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users-roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Override
    public Collection<? extends SimpleGrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE:" + role.getName())));

        roles.forEach(role ->
                        role.getPermissions().forEach(persmission ->
                                authorities.add(new SimpleGrantedAuthority(persmission.getSubject()+ ":" +persmission.getAction())))
        );
        System.out.println(authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
