package com.example.umc9th.domain.review.dto.res;

import com.example.umc9th.domain.review.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
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

    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Double score,
            String body,
            LocalDate createdAt
    ){}

    @Builder
    public record MyReviewPreViewListDTO(
            List<MyReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MyReviewPreViewDTO(
            String ownerNickname,
            Double score,
            String body,
            LocalDate createdAt,
            List<CommentResDTO.CommentDTO> comments
    ){}
}
