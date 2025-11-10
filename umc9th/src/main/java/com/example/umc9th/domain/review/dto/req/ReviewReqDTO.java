package com.example.umc9th.domain.review.dto.req;

import lombok.Builder;
import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    @Builder
    public static class newReview {
        private Long memberId;
        private String memberName;
        private Double star;
        private String content;
    }
}
