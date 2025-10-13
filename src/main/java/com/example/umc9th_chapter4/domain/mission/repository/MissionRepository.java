package com.example.umc9th_chapter4.domain.mission.repository;

import com.example.umc9th_chapter4.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {}