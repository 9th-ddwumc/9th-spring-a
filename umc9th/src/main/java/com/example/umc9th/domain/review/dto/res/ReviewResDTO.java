package com.example.umc9th.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReviewResDTO {

    @Builder
    @Getter
    public static class newReview {
        private Long reviewId;
        private Long memberId;
        private Long restaurantId;
        private Double star;
        private String content;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    public static class MyReview {
        private Long reviewId;
        private String username;
        private String content;
        private String restaurantName;
        private Double star;
        private List<MyReviewResCommentDto> comments = new ArrayList<>();
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    public static class MyReviewResCommentDto {

        private String content;
        private LocalDateTime createdAt;
    }
}
