package com.example.umc9th_chapter4.domain.mission.controller;

import com.example.umc9th_chapter4.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th_chapter4.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th_chapter4.domain.mission.service.command.MissionCommandService;
import com.example.umc9th_chapter4.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionCommandController {

    private final MissionCommandService missionCommandService;

    @Operation(
            summary = "미션 도전하기 API By 포롱 (개발중)",
            description = "사용자가 특정 미션을 도전합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "미션을 찾을 수 없음")
    })
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
