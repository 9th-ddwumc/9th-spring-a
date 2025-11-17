package com.example.umc9th_chapter4.domain.store.controller;

import com.example.umc9th_chapter4.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th_chapter4.domain.store.dto.res.StoreResDTO;
import com.example.umc9th_chapter4.domain.store.exception.code.StoreSuccessCode;
import com.example.umc9th_chapter4.domain.store.service.command.StoreCommandService;
import com.example.umc9th_chapter4.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class StoreCommandController {

    private final StoreCommandService storeCommandService;

    // 특정 지역에 가게 추가하기
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
