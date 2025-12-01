package com.example.umc9th_chapter4.domain.review.controller;

import com.example.umc9th_chapter4.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th_chapter4.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th_chapter4.domain.review.service.ReviewReadService;
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
@RequestMapping("/users/me")
@RequiredArgsConstructor
@Validated
public class ReviewReadController {

    private final ReviewReadService service;

    @Operation(
            summary = "내가 작성한 리뷰 목록 조회 API By 포롱 (개발중)",
            description = "현재 로그인한 사용자의 리뷰 목록을 페이징으로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Page 파라미터 오류")
    })
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestParam(required = false) String store,
            @RequestParam(required = false) Integer ratingBand,
            @RequestParam(defaultValue = "1") @PageValid Integer page
    ) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("createdAt").descending());

        ReviewResDTO.MyReviewListDTO result =
                service.getMyReviews(userId, store, ratingBand, pageable);

        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, result);
    }
}
