package com.example.umc9th_chapter4.domain.review.dto.req;

import java.util.List;

public class ReviewReqDTO {

    // 리뷰 작성 요청 DTO
    public record CreateDTO(
            Double rating,
            String content,
            List<String> photoUrls
    ) {}
}
