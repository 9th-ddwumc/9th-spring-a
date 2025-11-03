package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.restraunt.entity.QRestaurant;
import com.example.umc9th.domain.review.dto.MyReviewDto;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<MyReviewDto> getMyReviews(String restaurantName, Double star) {
        QReview review = QReview.review;
        QRestaurant restaurant = QRestaurant.restaurant;

        BooleanBuilder builder = new BooleanBuilder();

        if (restaurantName != null) {
            builder.and(restaurant.name.eq(restaurantName));
        }

        if (star != null) {
            builder.and(review.star.between(star, star + 0.9));
        }

        return reviewRepository.findMyReviewsWithFilter(builder);
    }
}
