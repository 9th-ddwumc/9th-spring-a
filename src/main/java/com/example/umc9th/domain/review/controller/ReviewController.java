package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController implements ReviewControllerDocs {

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

    @PostMapping(
            value = "/missions/{missionId}/reviews", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public ApiResponse<ReviewResDTO.newReview> addNewReview(
            @Parameter(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
            @RequestPart("request") @Valid ReviewReqDTO.newReview request,
            @PathVariable("missionId") Long missionId,
            @RequestPart("reviewImage") MultipartFile reviewImage
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                reviewCommandService.addNewReview(request, reviewImage)
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

    @Override
    public ApiResponse<Void> deleteReviewImage(Long reviewId) {
        reviewCommandService.deleteReviewImage(reviewId);
        ReviewSuccessCode code = ReviewSuccessCode.REVIEW_IMAGE_DELETED;
        return ApiResponse.onSuccess(code, null);
    }

}
