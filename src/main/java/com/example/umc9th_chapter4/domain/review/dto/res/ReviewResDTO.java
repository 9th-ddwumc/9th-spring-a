package com.example.umc9th_chapter4.domain.review.dto.res;

import java.util.List;

public class ReviewResDTO {

    // 리뷰 작성 응답 DTO
    public record CreateDTO(
            Long reviewId,
            Double rating,
            String content,
            List<String> photoUrls
    ) {}
}
