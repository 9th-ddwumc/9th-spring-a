package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.restraunt.entity.QRestaurant;
import com.example.umc9th.domain.restraunt.entity.Restaurant;
import com.example.umc9th.domain.restraunt.exception.RestaurantException;
import com.example.umc9th.domain.restraunt.exception.code.RestaurantErrorCode;
import com.example.umc9th.domain.restraunt.repository.RestaurantRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Comment;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.CommentRepository;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService{

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final ReviewConverter reviewConverter;

    @Override
    public List<ReviewResDTO.MyReview> getMyReviews(String restaurantName, Double star) {
        QReview review = QReview.review;
        QRestaurant restaurant = QRestaurant.restaurant;

        BooleanBuilder builder = new BooleanBuilder();

        if (restaurantName != null) {
            builder.and(restaurant.name.eq(restaurantName));
        }

        if (star != null) {
            builder.and(review.star.between(star, star + 0.9));
        }

        return reviewRepository.findMyReviewsWithFilter(builder);
    }

    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(String storeName, Integer page) {
        // - 가게를 가져온다 (가게 존재 여부 검증)
        Restaurant restaurant = restaurantRepository.findByName(storeName)
        //    - 없으면 예외 터뜨린다
                .orElseThrow(() -> new RestaurantException(RestaurantErrorCode.NOT_FOUND));

        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByRestaurant(restaurant, pageRequest);

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewPreviewListDTO(result);
    }

    @Override
    public ReviewResDTO.MyReviewPreViewListDTO findMyReview(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Review> result = reviewRepository.findAllByMember(member, pageRequest);

        if (result.isEmpty()) {
            return reviewConverter.toMyReviewPreviewListDTO(result, List.of());
        }

        List<Long> reviewIds = result.getContent().stream()
                .map(Review::getId)
                .toList();

        List<Comment> comments = commentRepository.findByReviewIdIn(reviewIds);

        return reviewConverter.toMyReviewPreviewListDTO(result, comments);
    }
}
