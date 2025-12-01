package com.example.umc9th_chapter4.domain.mission.service.command;

import com.example.umc9th_chapter4.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th_chapter4.domain.mission.dto.res.MissionResDTO;

public interface MissionCommandService {

    // 미션 도전하기
    MissionResDTO.ChallengeDTO challengeMission(Long missionId);

    // 미션 추가하기
    MissionResDTO.MissionCreateDTO createMission(Long storeId, MissionReqDTO.MissionCreateDTO dto);

    // 미션 완료하기
    MissionResDTO.CompletedMissionDTO completeMission(Long userId, Long userMissionId);
}
