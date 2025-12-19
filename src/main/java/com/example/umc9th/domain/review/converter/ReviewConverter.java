package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.restraunt.entity.Restaurant;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Comment;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.ReviewImage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReviewConverter {

    private final CommentConverter commentConverter;

    public ReviewResDTO.newReview toNewReview(Review review) {
        return ReviewResDTO.newReview.builder()
                .reviewId(review.getId())
                .memberId(review.getMember().getId())
                .restaurantId(review.getRestaurant().getId())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public Review toReview(Member member, Restaurant restaurant, ReviewReqDTO.newReview newReview) {
        return Review.builder()
                .member(member)
                .restaurant(restaurant)
                .star(newReview.getStar())
                .content(newReview.getContent())
                .build();
    }

    // result -> DTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getStar())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }

    // result -> DTO
    public ReviewResDTO.MyReviewPreViewListDTO toMyReviewPreviewListDTO(
            Page<Review> reviewPage,
            List<Comment> comments
    ){
        List<Review> reviews = reviewPage.getContent();

        Map<Long, List<Comment>> commentMap = comments.stream()
                .collect(Collectors.groupingBy(c -> c.getReview().getId()));

        List<ReviewResDTO.MyReviewPreViewDTO> dtoList = reviews.stream()
                .map(review -> toMyReviewPreviewDTO(
                        review,
                        commentMap.getOrDefault(review.getId(), List.of())
                ))
                .toList();

        return ReviewResDTO.MyReviewPreViewListDTO.builder()
                .reviewList(dtoList)
                .listSize(dtoList.size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }

    public ReviewResDTO.MyReviewPreViewDTO toMyReviewPreviewDTO(
            Review review,
            List<Comment> comments
    ){
        return ReviewResDTO.MyReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getStar())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .comments(commentConverter.toCommentDTOList(comments))
                .build();
    }

    public static ReviewImage toReviewImage(String pictureUrl, Review review) {
        return ReviewImage.builder()
                .imageUrl(pictureUrl)
                .review(review)
                .build();
    }
}
