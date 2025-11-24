package com.example.umc9th_chapter4.domain.review.dto.res;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

public class ReviewResDTO {

    // 리뷰 작성 응답 DTO
    public record CreateDTO(
            Long reviewId,
            Double rating,
            String content,
            List<String> photoUrls
    ) {}

    // 내가 작성한 리뷰 목록 조회 응답 DTO
    @Builder
    public record MyReviewListDTO(
            List<MyReviewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    // 내가 작성한 리뷰 개별 응답 DTO
    @Builder
    public record MyReviewDTO(
            String content,
            Double star,
            String reply
    ) {}
}
