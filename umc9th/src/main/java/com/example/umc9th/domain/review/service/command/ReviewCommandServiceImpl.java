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
    public ReviewResDTO.newReview addNewReview(Long restaurantId, ReviewReqDTO.newReview request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException(RestaurantErrorCode.RESTAURANT_NOT_FOUND));

        Review review = Review.builder()
                .member(member)
                .restaurant(restaurant)
                .star(request.getStar())
                .content(request.getContent())
                .build();

        Review savedReview = reviewRepository.save(review);
        return reviewConverter.toNewReview(savedReview);
    }
}
