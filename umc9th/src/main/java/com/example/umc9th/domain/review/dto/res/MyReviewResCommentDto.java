package com.example.umc9th.domain.review.dto.res;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyReviewResCommentDto {

    private String content;
    private LocalDateTime createdAt;
}
