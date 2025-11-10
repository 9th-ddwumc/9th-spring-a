package com.example.umc9th_chapter4.domain.review.repository;

import com.example.umc9th_chapter4.domain.review.entity.ReviewReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewReplyRepository extends JpaRepository<ReviewReply, Long> {

    @Query("select rr.content from ReviewReply rr where rr.review.id = :reviewId")
    String findContentByReviewId(Long reviewId);
}
