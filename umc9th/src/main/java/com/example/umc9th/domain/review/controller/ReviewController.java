package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.MyReviewDto;
import com.example.umc9th.domain.review.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/mypage/myreviews")
    public List<MyReviewDto> getMyReviews(
            @RequestParam(required = false) String restaurantName,
            @RequestParam(required = false) double star) {
        return reviewService.getMyReviews(restaurantName, star);
    }
}
