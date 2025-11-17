package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.restraunt.entity.QRestaurant;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.QComment;
import com.example.umc9th.domain.review.entity.QReview;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl{

    private final EntityManager em;

    @Override
    public List<ReviewResDTO.MyReview> findMyReviewsWithFilter(BooleanBuilder builder) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QReview review = QReview.review;
        QRestaurant restaurant = QRestaurant.restaurant;
        QComment comment = QComment.comment;

        List<Tuple> tuples = queryFactory
                .select(review.id,
                        review.member.name,
                        review.content,
                        review.restaurant.name,
                        review.star,
                        comment.content.as("commentContent"),
                        comment.createdAt.as("commentCreatedAt"),
                        review.createdAt.as("reviewCreatedAt"))
                .from(review)
                .join(review.restaurant, restaurant)
                .leftJoin(review.comments, comment)
                .where(builder)
                .fetch();

        Map<Long, ReviewResDTO.MyReview> reviewMap = new HashMap<>();

        for (Tuple t : tuples) {
            Long reviewId = t.get(review.id);
            ReviewResDTO.MyReview myReviewDto = reviewMap.get(reviewId);
            if (myReviewDto == null) {
                myReviewDto =  ReviewResDTO.MyReview.builder()
                        .reviewId(reviewId)
                        .username(t.get(review.member.name))
                        .content(t.get(review.content))
                        .restaurantName(t.get(review.restaurant.name))
                        .star(t.get(review.star))
                        .comments(new ArrayList<>())
                        .createdAt(t.get(review.createdAt))
                        .build();
                reviewMap.put(reviewId, myReviewDto);
            }

            String content = t.get(comment.content);
            LocalDateTime createdAt = t.get(comment.createdAt);

            if (content != null) {
                myReviewDto.getComments().add(
                        ReviewResDTO.MyReviewResCommentDto.builder()
                                .content(content)
                                .createdAt(createdAt)
                                .build()
                );
            }
        }
        return new ArrayList<>(reviewMap.values());
    }
}
