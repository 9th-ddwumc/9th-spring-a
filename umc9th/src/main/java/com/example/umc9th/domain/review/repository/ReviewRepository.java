package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAll();

    @Query("select r.member.name, r.restaurant, r.star, r.content, r.createdAt from Review r")
    List<Review> findAllReviews();
}
