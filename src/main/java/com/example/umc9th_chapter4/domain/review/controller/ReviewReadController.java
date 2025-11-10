package com.example.umc9th_chapter4.domain.review.controller;

import com.example.umc9th_chapter4.domain.review.dto.MyReviewRowDto;
import com.example.umc9th_chapter4.domain.review.service.ReviewReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewReadController {

    private final ReviewReadService service;

    @GetMapping("/me")
    public Page<MyReviewRowDto> getMyReviews(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestParam(required = false) String store,
            @RequestParam(required = false) Integer ratingBand,
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable
    ) {
        return service.getMyReviews(userId, store, ratingBand, pageable);
    }
}
