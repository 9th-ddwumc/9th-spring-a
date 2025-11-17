package com.example.umc9th_chapter4.domain.mission.controller;

import com.example.umc9th_chapter4.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th_chapter4.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th_chapter4.domain.mission.service.command.MissionCommandService;
import com.example.umc9th_chapter4.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionCommandController {

    private final MissionCommandService missionCommandService;

    // 미션 도전하기
    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MissionResDTO.ChallengeDTO> challengeMission(
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.CHALLENGED,
                missionCommandService.challengeMission(missionId)
        );
    }
}
