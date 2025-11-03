package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.MyReviewDto;
import com.querydsl.core.BooleanBuilder;

import java.util.List;

public interface ReviewQueryDsl {

    List<MyReviewDto> findMyReviewsWithFilter(BooleanBuilder builder);
}
