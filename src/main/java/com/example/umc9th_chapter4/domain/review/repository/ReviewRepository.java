package com.example.umc9th_chapter4.domain.review.repository;

import com.example.umc9th_chapter4.domain.review.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 마이페이지: 내 리뷰(최신순, 페이징)
    Page<Review> findByUser_UserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);

    // 매장 리뷰 목록
    List<Review> findByStore_Id(Long storeId);

    // 매장 평균 평점
    @Query("select avg(r.rating) from Review r where r.store.id = :storeId")
    Double findAverageRatingByStoreId(Long storeId);

    // 리뷰 + 사진(배제 가능) 프리로드 예시 — 사진은 현재 요구사항에서 배제
    @Query("select distinct r from Review r left join fetch r.reply where r.store.id = :storeId")
    List<Review> findWithReplyByStoreId(Long storeId);

    // 내가 특정 매장에 남긴 리뷰 단건
    Optional<Review> findByUser_UserIdAndStore_Id(Long userId, Long storeId);

    // 대안: 엔티티그래프(사진/답글 모두 필요할 때)
    @EntityGraph(attributePaths = {"reply"})
    Page<Review> findByUser_UserId(Long userId, Pageable pageable);
}
