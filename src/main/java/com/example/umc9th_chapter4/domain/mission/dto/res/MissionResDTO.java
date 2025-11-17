package com.example.umc9th_chapter4.domain.mission.dto.res;

import com.example.umc9th_chapter4.domain.mission.enums.MissionStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
