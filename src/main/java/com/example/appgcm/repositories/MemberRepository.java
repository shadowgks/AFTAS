package com.example.appgcm.repositories;

import com.example.appgcm.models.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByIdentityNumber(String identityNumber);
    @Query("SELECT m FROM Member m " +
            "WHERE LOWER(m.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(m.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(m.identityNumber) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Member> searchMembers(@Param("searchTerm") String searchTerm);
}
