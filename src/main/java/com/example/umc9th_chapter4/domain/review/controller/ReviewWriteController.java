package com.example.umc9th_chapter4.domain.review.controller;

import com.example.umc9th_chapter4.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th_chapter4.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th_chapter4.domain.review.service.command.ReviewCommandService;
import com.example.umc9th_chapter4.global.apiPayload.ApiResponse;
import com.example.umc9th_chapter4.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class ReviewWriteController {

    private final ReviewCommandService reviewCommandService;

    // 가게에 리뷰 작성
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
