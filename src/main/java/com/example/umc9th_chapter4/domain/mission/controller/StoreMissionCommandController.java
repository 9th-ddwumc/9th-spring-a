package com.example.umc9th_chapter4.domain.mission.controller;

import com.example.umc9th_chapter4.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th_chapter4.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th_chapter4.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th_chapter4.domain.mission.service.command.MissionCommandService;
import com.example.umc9th_chapter4.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreMissionCommandController {

    private final MissionCommandService missionCommandService;

    // 가게에 미션 추가하기
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
