package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController implements ReviewControllerDocs{

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

    @PostMapping("/missions/{missionId}/reviews")
    public ApiResponse<ReviewResDTO.newReview> addNewReview(
            @PathVariable("missionId") Long missionId,
            @RequestBody @Valid ReviewReqDTO.newReview request
            ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                reviewCommandService.addNewReview(request)
        );
    }

    // 가게의 리뷰 목록 조회
    @Override
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam("storeName") String storeName,
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ) {
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, page));
    }

    @Override
    public ApiResponse<ReviewResDTO.MyReviewPreViewListDTO> getMyReviews(Long memberId, Integer page) {
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        page--;
        return ApiResponse.onSuccess(code, reviewQueryService.findMyReview(memberId, page));
    }


}
