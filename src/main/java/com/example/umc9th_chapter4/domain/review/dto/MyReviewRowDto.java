package com.example.umc9th_chapter4.domain.review.dto;

public record MyReviewRowDto(
        Long id,          // review id
        String content,   // 리뷰 내용
        Double star,      // rating
        String reply      // 사장님 답글(없으면 null)
) {}
