package com.example.appgcm.repositories;

import com.example.appgcm.models.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmailOrUserNamee(String email, String username);
    Optional<AppUser> findByIdentityNumber(String identityNumber);
    @Query("SELECT m FROM AppUser m " +
            "WHERE LOWER(m.fullName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(m.identityNumber) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<AppUser> searchMembers(@Param("searchTerm") String searchTerm);
}
