package com.example.umc9th_chapter4.domain.review.repository;

import com.example.umc9th_chapter4.domain.review.entity.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewPhotoRepository extends JpaRepository<ReviewPhoto, Long> {
    List<ReviewPhoto> findByReview_Id(Long reviewId);
}
