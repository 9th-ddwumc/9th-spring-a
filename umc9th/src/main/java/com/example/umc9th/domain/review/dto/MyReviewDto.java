package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.review.entity.Comment;
import com.example.umc9th.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MyReviewDto {
    private Long reviewId;
    private String username;
    private String content;
    private String restaurantName;
    private Double star;
    private List<MyReviewCommentDto> comments = new ArrayList<>();
    private LocalDateTime createdAt;
}
