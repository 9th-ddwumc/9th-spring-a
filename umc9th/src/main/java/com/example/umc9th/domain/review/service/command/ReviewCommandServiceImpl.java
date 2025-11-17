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
import com.example.umc9th.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService{

    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewConverter reviewConverter;

    @Override
    @Transactional
    public ReviewResDTO.newReview addNewReview(ReviewReqDTO.newReview request) {
        Member member = memberRepository.getReferenceById(request.getMemberId());
        Restaurant restaurant = restaurantRepository.getReferenceById(request.getRestaurantId());

        Review review = reviewConverter.toReview(member, restaurant, request);

        Review savedReview = reviewRepository.save(review);
        return reviewConverter.toNewReview(savedReview);
    }
}
