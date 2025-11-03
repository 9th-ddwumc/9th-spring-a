package com.example.umc9th_chapter4.domain.review.repository;

import com.example.umc9th_chapter4.domain.review.dto.MyReviewRowDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryRepository {
    /**
     * 내가 작성한 리뷰 단일 API (가게명/별점밴드 동시 필터)
     * @param loginUserId 로그인 사용자 PK (헤더·시큐리티에서 전달)
     * @param storeName   옵션. 예) "반이학생마라탕마라반"
     * @param ratingBand  옵션. 예) 5,4,3,2,1  => [band, band+1) 구간
     */
    Page<MyReviewRowDto> findMyReviews(Long loginUserId, String storeName, Integer ratingBand, Pageable pageable);
}
