package com.example.umc9th_chapter4.domain.mission.controller;

import com.example.umc9th_chapter4.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th_chapter4.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th_chapter4.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th_chapter4.domain.mission.service.command.MissionCommandService;
import com.example.umc9th_chapter4.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreMissionCommandController {

    private final MissionCommandService missionCommandService;

    @Operation(
            summary = "가게에 미션 추가하기 API By 포롱 (개발중)",
            description = "특정 가게에 새로운 미션을 추가합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "가게를 찾을 수 없음")
    })
    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResDTO.MissionCreateDTO> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionReqDTO.MissionCreateDTO dto
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.CREATED,
                missionCommandService.createMission(storeId, dto)
        );
    }
}
