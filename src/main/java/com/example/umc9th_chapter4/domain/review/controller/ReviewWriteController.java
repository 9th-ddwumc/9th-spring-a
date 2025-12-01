package com.example.umc9th_chapter4.domain.review.controller;

import com.example.umc9th_chapter4.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th_chapter4.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th_chapter4.domain.review.service.command.ReviewCommandService;
import com.example.umc9th_chapter4.global.apiPayload.ApiResponse;
import com.example.umc9th_chapter4.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class ReviewWriteController {

    private final ReviewCommandService reviewCommandService;

    @Operation(
            summary = "가게에 리뷰 작성 API By 포롱 (개발중)",
            description = "특정 가게에 새로운 리뷰를 작성합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "가게를 찾을 수 없음")
    })
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.CreateDTO dto
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewCommandService.createReview(storeId, dto)
        );
    }
}
