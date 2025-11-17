package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.restraunt.entity.Restaurant;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReviewConverter {
    public ReviewResDTO.newReview toNewReview(Review review) {
        return ReviewResDTO.newReview.builder()
                .reviewId(review.getId())
                .memberId(review.getMember().getId())
                .restaurantId(review.getRestaurant().getId())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public Review toReview(Member member, Restaurant restaurant, ReviewReqDTO.newReview newReview) {
        return Review.builder()
                .member(member)
                .restaurant(restaurant)
                .star(newReview.getStar())
                .content(newReview.getContent())
                .build();
    }
}
