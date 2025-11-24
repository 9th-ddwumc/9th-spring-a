package com.example.umc9th_chapter4.domain.mission.controller;

import com.example.umc9th_chapter4.domain.mission.dto.res.MissionResDTO;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
@Validated
public class MissionReadController {

    private final MissionReadService service;

    @Operation(
            summary = "특정 가게의 미션 목록 조회 API By 포롱 (개발중)",
            description = "특정 가게의 미션 목록을 페이징으로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Page 파라미터 오류")
    })
    @GetMapping("/{storeId}/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getMissions(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "1") @PageValid Integer page
    ) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("createdAt").descending());
        MissionResDTO.MissionListDTO result = service.getMissionsByStore(storeId, pageable);
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, result);
    }
}
