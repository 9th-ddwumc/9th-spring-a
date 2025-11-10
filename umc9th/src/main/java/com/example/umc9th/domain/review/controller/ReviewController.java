package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/{memberId}/mypage/myreviews")
    public ApiResponse<List<ReviewResDTO.MyReview>> getMyReviews(
            @RequestParam(required = false) String restaurantName,
            @RequestParam(required = false) double star
    ) {
        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(
                code,
                reviewQueryService.getMyReviews(restaurantName, star)
        );
    }

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ApiResponse<ReviewResDTO.newReview> addNewReview(
            @PathVariable("restaurantId") Long restaurantId,
            @RequestBody ReviewReqDTO.newReview request
            ) {
        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(
                code,
                reviewCommandService.addNewReview(restaurantId, request)
        );
    }

}
