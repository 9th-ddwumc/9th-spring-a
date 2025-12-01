package com.example.umc9th.domain.review.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class CommentResDTO {

    @Builder
    public record CommentDTO(
            Long id,
            String content,
            LocalDateTime createdAt
    ) {}

}
