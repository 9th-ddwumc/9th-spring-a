package com.example.umc9th_chapter4.domain.store.controller;

import com.example.umc9th_chapter4.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th_chapter4.domain.store.dto.res.StoreResDTO;
import com.example.umc9th_chapter4.domain.store.exception.code.StoreSuccessCode;
import com.example.umc9th_chapter4.domain.store.service.command.StoreCommandService;
import com.example.umc9th_chapter4.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class StoreCommandController {

    private final StoreCommandService storeCommandService;

    @Operation(
            summary = "특정 지역에 가게 추가하기 API By 포롱 (개발중)",
            description = "특정 지역에 새로운 가게를 추가합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "지역을 찾을 수 없음")
    })
    @PostMapping("/{locationId}/stores")
    public ApiResponse<StoreResDTO.StoreCreateDTO> createStore(
            @PathVariable Long locationId,
            @RequestBody StoreReqDTO.StoreCreateDTO dto
    ) {
        return ApiResponse.onSuccess(
                StoreSuccessCode.CREATED,
                storeCommandService.createStore(locationId, dto)
        );
    }
}
