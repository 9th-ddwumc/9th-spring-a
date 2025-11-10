package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.res.MyReviewResDto;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.querydsl.core.BooleanBuilder;

import java.util.List;

public interface ReviewQueryDsl {

    List<ReviewResDTO.MyReview> findMyReviewsWithFilter(BooleanBuilder builder);
}
