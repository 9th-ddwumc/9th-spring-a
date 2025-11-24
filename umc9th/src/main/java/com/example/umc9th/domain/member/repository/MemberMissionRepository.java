package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.enums.MemberMissionStatus;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Page<MemberMission> findByMemberAndStatus(Member member, MemberMissionStatus status, PageRequest pageRequest);

    Optional<MemberMission> findByMemberAndMission(Member member, Mission mission);

    Page<MemberMission> findByMemberAndMission(Member member, Mission mission, PageRequest pageRequest);
}
