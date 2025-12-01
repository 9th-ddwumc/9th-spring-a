package com.example.umc9th_chapter4.domain.review.service;

import com.example.umc9th_chapter4.domain.review.converter.ReviewConverter;
import com.example.umc9th_chapter4.domain.review.dto.MyReviewRowDto;
import com.example.umc9th_chapter4.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th_chapter4.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewReadService {
    private final ReviewRepository reviewRepository;

    public ReviewResDTO.MyReviewListDTO getMyReviews(Long loginUserId, String store, Integer ratingBand, Pageable pageable) {
        Page<MyReviewRowDto> page = reviewRepository.findMyReviews(loginUserId, store, ratingBand, pageable);
        return ReviewConverter.toMyReviewListDTO(page);
    }
}
