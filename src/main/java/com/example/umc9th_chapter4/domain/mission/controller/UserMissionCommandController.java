package com.example.umc9th_chapter4.domain.mission.controller;

import com.example.umc9th_chapter4.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th_chapter4.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th_chapter4.domain.mission.service.command.MissionCommandService;
import com.example.umc9th_chapter4.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/me")
@RequiredArgsConstructor
public class UserMissionCommandController {

    private final MissionCommandService missionCommandService;

    @Operation(
            summary = "미션 완료 API By 포롱 (개발중)",
            description = "진행 중인 미션을 완료하고 상태를 변경합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "미션 진행을 찾을 수 없음")
    })
    @PatchMapping("/missions/{userMissionId}/complete")
    public ApiResponse<MissionResDTO.CompletedMissionDTO> completeMission(
            @RequestHeader("X-USER-ID") Long userId,
            @PathVariable Long userMissionId
    ) {
        MissionResDTO.CompletedMissionDTO result = missionCommandService.completeMission(userId, userMissionId);
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, result);
    }
}
