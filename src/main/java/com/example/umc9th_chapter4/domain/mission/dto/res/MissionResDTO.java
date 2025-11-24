package com.example.umc9th_chapter4.domain.mission.dto.res;

import com.example.umc9th_chapter4.domain.mission.enums.MissionStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionCreateDTO(
            Long missionId,
            Long storeId,
            String title,
            String description,
            Integer point,
            LocalDate deadline
    ) {}

    // 미션 도전 응답 DTO
    @Builder
    public record ChallengeDTO(
            Long userMissionId,
            Long missionId,
            Long userId,
            MissionStatus status,
            LocalDateTime startedAt
    ) {}

    // 특정 가게의 미션 개별 응답 DTO
    @Builder
    public record MissionDTO(
            Long missionId,
            String title,
            String description,
            Integer point,
            LocalDate deadline
    ) {}

    // 특정 가게의 미션 목록 조회 응답 DTO
    @Builder
    public record MissionListDTO(
            List<MissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    // 사용자의 진행 중인 미션 개별 응답 DTO
    @Builder
    public record UserMissionDTO(
            Long userMissionId,
            Long missionId,
            String title,
            String description,
            Integer point,
            LocalDate deadline,
            String status
    ) {}

    // 사용자의 진행 중인 미션 목록 조회 응답 DTO
    @Builder
    public record UserMissionListDTO(
            List<UserMissionDTO> userMissionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    // 미션 완료 응답 DTO
    @Builder
    public record CompletedMissionDTO(
            Long userMissionId,
            Long missionId,
            String title,
            String description,
            Integer point,
            LocalDate deadline,
            String status,
            LocalDateTime clearedAt
    ) {}
}
