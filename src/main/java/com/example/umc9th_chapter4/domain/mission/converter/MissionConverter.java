package com.example.umc9th_chapter4.domain.mission.converter;

import com.example.umc9th_chapter4.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th_chapter4.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th_chapter4.domain.mission.entity.Mission;
import com.example.umc9th_chapter4.domain.mission.entity.UserMission;
import com.example.umc9th_chapter4.domain.store.entity.Store;
import com.example.umc9th_chapter4.domain.user.entity.Users;
import java.time.LocalDateTime;

public class MissionConverter {

    public static UserMission toUserMission(Mission mission, Users user) {
        return UserMission.builder()
                .mission(mission)
                .users(user)
                .startedAt(LocalDateTime.now())
                .build();
    }

    public static MissionResDTO.ChallengeDTO toChallengeDTO(UserMission userMission) {
        return new MissionResDTO.ChallengeDTO(
                userMission.getId(),
                userMission.getMission().getId(),
                userMission.getUsers().getUserId(),
                userMission.getStatus(),
                userMission.getStartedAt()
        );
    }

    // 미션 생성용 DTO -> Mission
    public static Mission toMission(MissionReqDTO.MissionCreateDTO dto, Store store) {
        return Mission.builder()
                .store(store)
                .title(dto.title())
                .description(dto.description())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    // Mission 엔티티 -> 응답 DTO
    public static MissionResDTO.MissionCreateDTO toMissionCreateDTO(Mission mission) {
        return MissionResDTO.MissionCreateDTO.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }
}
