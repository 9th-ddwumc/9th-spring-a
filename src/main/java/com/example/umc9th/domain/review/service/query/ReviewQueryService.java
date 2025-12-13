package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;

import java.util.List;

public interface ReviewQueryService {

    List<ReviewResDTO.MyReview> getMyReviews(String restaurantName, Double star);

    ReviewResDTO.ReviewPreViewListDTO findReview(String storeName, Integer page);

    ReviewResDTO.MyReviewPreViewListDTO findMyReview(Long memberId, Integer page);
}
