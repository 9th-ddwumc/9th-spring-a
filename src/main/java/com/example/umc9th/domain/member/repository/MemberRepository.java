package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(Long id);

    @Query("select m.name, m.email, m.phone, m.point from Member m where m.id = :id")
    Optional<Member> findById2(@Param("id") Long id);

    Optional<Member> findByEmail(String email);
}
