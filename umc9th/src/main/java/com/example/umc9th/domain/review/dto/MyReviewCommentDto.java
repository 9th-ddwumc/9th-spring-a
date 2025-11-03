package com.example.umc9th.domain.review.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyReviewCommentDto {

    private String content;
    private LocalDateTime createdAt;
}
