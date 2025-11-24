package com.example.umc9th_chapter4.domain.mission.service.query;

import com.example.umc9th_chapter4.domain.mission.converter.MissionConverter;
import com.example.umc9th_chapter4.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th_chapter4.domain.mission.entity.Mission;
import com.example.umc9th_chapter4.domain.mission.entity.UserMission;
import com.example.umc9th_chapter4.domain.mission.enums.MissionStatus;
import com.example.umc9th_chapter4.domain.mission.repository.MissionRepository;
import com.example.umc9th_chapter4.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionReadService {
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    // 특정 가게의 미션 목록 조회 (페이징)
    public MissionResDTO.MissionListDTO getMissionsByStore(Long storeId, Pageable pageable) {
        Page<Mission> page = missionRepository.findByStore_IdOrderByCreatedAtDesc(storeId, pageable);
        return MissionConverter.toMissionListDTO(page);
    }

    // 사용자의 진행 중인 미션 목록 조회 (페이징)
    public MissionResDTO.UserMissionListDTO getUserMissions(Long userId, MissionStatus status, Pageable pageable) {
        Page<UserMission> page = userMissionRepository.findByUsers_UserIdAndStatusOrderByUpdatedAtDesc(userId, status, pageable);
        return MissionConverter.toUserMissionListDTO(page);
    }
}
