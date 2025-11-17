package com.example.umc9th.domain.review.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MyReviewResDto {
    private Long reviewId;
    private String username;
    private String content;
    private String restaurantName;
    private Double star;
    private List<MyReviewResCommentDto> comments = new ArrayList<>();
    private LocalDateTime createdAt;
}
