package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.restraunt.entity.Restaurant;
import com.example.umc9th.domain.restraunt.exception.RestaurantException;
import com.example.umc9th.domain.restraunt.exception.code.RestaurantErrorCode;
import com.example.umc9th.domain.restraunt.repository.RestaurantRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.ReviewImage;
import com.example.umc9th.domain.review.exception.ReviewException;
import com.example.umc9th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc9th.domain.review.repository.ReviewImageRepository;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.global.aws.s3.AmazonS3Manager;
import com.example.umc9th.global.entity.Uuid;
import com.example.umc9th.global.entity.repository.UuidRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService{

    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewConverter reviewConverter;
    private final AmazonS3Manager s3Manager;
    private final UuidRepository uuidRepository;
    private final ReviewImageRepository reviewImageRepository;

    @Override
    @Transactional
    public ReviewResDTO.newReview addNewReview(ReviewReqDTO.newReview request, MultipartFile reviewPicture) {
        Member member = memberRepository.getReferenceById(request.getMemberId());
        Restaurant restaurant = restaurantRepository.getReferenceById(request.getRestaurantId());

        Review review = reviewConverter.toReview(member, restaurant, request);

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), reviewPicture);

        reviewImageRepository.save(ReviewConverter.toReviewImage(pictureUrl, review));
        Review savedReview = reviewRepository.save(review);
        return reviewConverter.toNewReview(savedReview);
    }

    @Override
    public void deleteReviewImage(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.NOT_FOUND));

        ReviewImage reviewImage = reviewImageRepository.findByReview(review)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_IMAGE_NOT_FOUND));

        s3Manager.deleteFile(reviewImage.getImageUrl());

        reviewImageRepository.delete(reviewImage);
    }
}
