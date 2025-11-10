package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {
    public ReviewResDTO.newReview toNewReview(Review review) {
        return ReviewResDTO.newReview.builder()
                .reviewId(review.getId())
                .memberId(review.getMember().getId())
                .restaurantId(review.getRestaurant().getId())
                .star(review.getStar())
                .content(review.getContent())
                .build();
    }
}
