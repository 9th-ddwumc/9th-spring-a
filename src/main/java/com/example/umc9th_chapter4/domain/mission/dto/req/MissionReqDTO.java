package com.example.umc9th_chapter4.domain.mission.dto.req;

import java.time.LocalDate;

public class MissionReqDTO {

    // 미션 생성 요청 DTO
    public record MissionCreateDTO(
            String title,
            String description,
            Integer point,
            LocalDate deadline
    ) {}
}
