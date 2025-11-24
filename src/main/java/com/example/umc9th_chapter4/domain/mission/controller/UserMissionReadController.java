package com.example.umc9th_chapter4.domain.mission.controller;

import com.example.umc9th_chapter4.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th_chapter4.domain.mission.enums.MissionStatus;
import com.example.umc9th_chapter4.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th_chapter4.domain.mission.service.query.MissionReadService;
import com.example.umc9th_chapter4.global.apiPayload.ApiResponse;
import com.example.umc9th_chapter4.global.validator.PageValid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/me")
@RequiredArgsConstructor
@Validated
public class UserMissionReadController {

    private final MissionReadService missionReadService;

    @Operation(
            summary = "진행 중인 미션 목록 조회 API By 포롱 (개발중)",
            description = "현재 사용자의 진행 중인 미션 목록을 페이징으로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 page 값")
    })
    @GetMapping("/missions")
    public ApiResponse<MissionResDTO.UserMissionListDTO> getUserMissions(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestParam(defaultValue = "IN_PROGRESS") MissionStatus status,
            @RequestParam(defaultValue = "1") @PageValid Integer page
    ) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("updatedAt").descending());

        MissionResDTO.UserMissionListDTO result =
                missionReadService.getUserMissions(userId, status, pageable);

        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, result);
    }
}
