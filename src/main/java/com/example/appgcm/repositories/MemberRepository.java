package com.example.appgcm.repositories;

import com.example.appgcm.models.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
