package com.example.umc9th.domain.review.dto.req;

import com.example.umc9th.global.annotation.ExistMembers;
import com.example.umc9th.global.annotation.ExistRestaurants;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    @Builder
    public static class newReview {
        @ExistMembers
        private Long memberId;
        @ExistRestaurants
        private Long restaurantId;
        @Min(1) @Max(5)
        private Double star;
        @NotBlank
        private String content;
    }
}
