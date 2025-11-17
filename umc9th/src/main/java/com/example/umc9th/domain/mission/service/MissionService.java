package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;

    public List<Mission> getSuccessOrNotSuccessMissions(
            Long memberId, boolean success, LocalDate lastEndDate,
            Long lastId, int pageSize
    ) {
        Pageable pageable = PageRequest.of(0, pageSize);

        if (lastEndDate != null && lastId != null) {
            return missionRepository.findMissionByCursor(memberId, success, lastEndDate, lastId, pageable);
        } else {
            return missionRepository.findFirstMission(memberId, success, pageable);
        }
    }
}
