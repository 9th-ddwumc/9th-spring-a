package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Validated
public interface ReviewControllerDocs {

    // 가게의 리뷰 목록 조회
    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 레미 (개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/reviews")
    ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam("storeName") String storeName,
            @RequestParam("page") Integer page
    );

    // 내가 쓴 리뷰 목록 조회
    @Operation(
            summary = "내가 쓴 리뷰 목록 조회 API By 레미 (개발 완료)",
            description = "내가 쓴 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/{memberId}/myreviews")
    ApiResponse<ReviewResDTO.MyReviewPreViewListDTO> getMyReviews(
            @PathVariable("memberId") Long memberId,
            @RequestParam("page") @ValidPage Integer page
    );

    // 리뷰 사진 삭제
    @Operation(
            summary = "리뷰 사진 삭제 API By 레미 (개발 완료)",
            description = "리뷰 사진을 삭제합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @DeleteMapping("/reviews/{reviewId}/image")
    ApiResponse<Void> deleteReviewImage(
            @PathVariable Long reviewId
    );


}
