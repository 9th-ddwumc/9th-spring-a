package com.example.umc9th_chapter4.domain.review.service.command;

import com.example.umc9th_chapter4.domain.review.converter.ReviewConverter;
import com.example.umc9th_chapter4.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th_chapter4.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th_chapter4.domain.review.entity.Review;
import com.example.umc9th_chapter4.domain.review.entity.ReviewPhoto;
import com.example.umc9th_chapter4.domain.review.exception.ReviewException;
import com.example.umc9th_chapter4.domain.review.exception.code.ReviewErrorCode;
import com.example.umc9th_chapter4.domain.review.repository.ReviewPhotoRepository;
import com.example.umc9th_chapter4.domain.review.repository.ReviewRepository;
import com.example.umc9th_chapter4.domain.store.entity.Store;
import com.example.umc9th_chapter4.domain.store.repository.StoreRepository;
import com.example.umc9th_chapter4.domain.user.entity.Users;
import com.example.umc9th_chapter4.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    // 로그인 대신 하드코딩 유저
    private static final Long MOCK_USER_ID = 1L;

    @Override
    @Transactional
    public ReviewResDTO.CreateDTO createReview(Long storeId, ReviewReqDTO.CreateDTO dto) {

        // 1. 하드코딩 유저 조회
        Users user = userRepository.findById(MOCK_USER_ID)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.MOCK_USER_NOT_FOUND));

        // 2. 가게 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.STORE_NOT_FOUND));

        // 3. 리뷰 엔티티 생성 & 저장
        Review review = ReviewConverter.toReview(dto, user, store);
        reviewRepository.save(review);

        // 4. 사진 있으면 ReviewPhoto 생성 & 저장
        List<ReviewPhoto> photos = ReviewConverter.toReviewPhotos(review, dto.photoUrls());
        if (!photos.isEmpty()) {
            reviewPhotoRepository.saveAll(photos);
            review.getPhotos().addAll(photos);
        }

        // 5. 응답 DTO 변환
        return ReviewConverter.toCreateDTO(review);
    }
}
