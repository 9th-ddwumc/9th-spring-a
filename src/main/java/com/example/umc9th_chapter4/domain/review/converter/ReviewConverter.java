package com.example.umc9th_chapter4.domain.review.converter;

import com.example.umc9th_chapter4.domain.review.dto.MyReviewRowDto;
import com.example.umc9th_chapter4.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th_chapter4.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th_chapter4.domain.review.entity.Review;
import com.example.umc9th_chapter4.domain.review.entity.ReviewPhoto;
import com.example.umc9th_chapter4.domain.store.entity.Store;
import com.example.umc9th_chapter4.domain.user.entity.Users;
import org.springframework.data.domain.Page;

import java.util.List;

public class ReviewConverter {

    // DTO -> Review 엔티티
    public static Review toReview(ReviewReqDTO.CreateDTO dto, Users user, Store store) {
        return Review.builder()
                .user(user)
                .store(store)
                .rating(dto.rating())
                .content(dto.content())
                .build();
    }

    // 사진 URL 리스트 -> ReviewPhoto 리스트
    public static List<ReviewPhoto> toReviewPhotos(Review review, List<String> photoUrls) {
        if (photoUrls == null || photoUrls.isEmpty()) return List.of();

        return photoUrls.stream()
                .map(url -> ReviewPhoto.builder()
                        .review(review)
                        .reviewPhotoUrl(url)
                        .build()
                )
                .toList();
    }

    // 엔티티 -> 응답 DTO
    public static ReviewResDTO.CreateDTO toCreateDTO(Review review) {
        List<String> photoUrls = review.getPhotos().stream()
                .map(ReviewPhoto::getReviewPhotoUrl)
                .toList();

        return new ReviewResDTO.CreateDTO(
                review.getId(),
                review.getRating(),
                review.getContent(),
                photoUrls
        );
    }

    // 내 리뷰 개별 변환 - MyReviewRowDto -> MyReviewDTO
    public static ReviewResDTO.MyReviewDTO toMyReviewDTO(MyReviewRowDto row) {
        return ReviewResDTO.MyReviewDTO.builder()
                .content(row.content())
                .star(row.star())
                .reply(row.reply())
                .build();
    }

    // 내 리뷰 목록 조회 - Page<MyReviewRowDto> -> MyReviewListDTO
    public static ReviewResDTO.MyReviewListDTO toMyReviewListDTO(Page<MyReviewRowDto> page) {
        return ReviewResDTO.MyReviewListDTO.builder()
                .reviewList(
                        page.getContent().stream()
                                .map(ReviewConverter::toMyReviewDTO)
                                .toList()
                )
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
