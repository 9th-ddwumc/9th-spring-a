package com.example.umc9th_chapter4.domain.mission.converter;

import com.example.umc9th_chapter4.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th_chapter4.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th_chapter4.domain.mission.entity.Mission;
import com.example.umc9th_chapter4.domain.mission.entity.UserMission;
import com.example.umc9th_chapter4.domain.store.entity.Store;
import com.example.umc9th_chapter4.domain.user.entity.Users;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
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

    // 특정 가게의 미션 개별 변환 - Mission -> MissionDTO
    public static MissionResDTO.MissionDTO toMissionDTO(Mission mission) {
        return MissionResDTO.MissionDTO.builder()
                .missionId(mission.getId())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }

    // 특정 가게의 미션 목록 조회 - Page<Mission> -> MissionListDTO
    public static MissionResDTO.MissionListDTO toMissionListDTO(Page<Mission> page) {
        return MissionResDTO.MissionListDTO.builder()
                .missionList(
                        page.getContent().stream()
                                .map(MissionConverter::toMissionDTO)
                                .toList()
                )
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    // 사용자의 진행 중인 미션 개별 변환 - UserMission -> UserMissionDTO
    public static MissionResDTO.UserMissionDTO toUserMissionDTO(UserMission userMission) {
        return MissionResDTO.UserMissionDTO.builder()
                .userMissionId(userMission.getId())
                .missionId(userMission.getMission().getId())
                .title(userMission.getMission().getTitle())
                .description(userMission.getMission().getDescription())
                .point(userMission.getMission().getPoint())
                .deadline(userMission.getMission().getDeadline())
                .status(userMission.getStatus().name())
                .build();
    }

    // 사용자의 진행 중인 미션 목록 조회 - Page<UserMission> -> UserMissionListDTO
    public static MissionResDTO.UserMissionListDTO toUserMissionListDTO(Page<UserMission> page) {
        return MissionResDTO.UserMissionListDTO.builder()
                .userMissionList(
                        page.getContent().stream()
                                .map(MissionConverter::toUserMissionDTO)
                                .toList()
                )
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    // 미션 완료 - UserMission -> CompletedMissionDTO
    public static MissionResDTO.CompletedMissionDTO toCompletedMissionDTO(UserMission userMission) {
        return MissionResDTO.CompletedMissionDTO.builder()
                .userMissionId(userMission.getId())
                .missionId(userMission.getMission().getId())
                .title(userMission.getMission().getTitle())
                .description(userMission.getMission().getDescription())
                .point(userMission.getMission().getPoint())
                .deadline(userMission.getMission().getDeadline())
                .status(userMission.getStatus().name())
                .clearedAt(userMission.getClearedAt())
                .build();
    }
}
