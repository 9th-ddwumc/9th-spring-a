package com.example.umc9th_chapter4.domain.review.service.command;

import com.example.umc9th_chapter4.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th_chapter4.domain.review.dto.res.ReviewResDTO;

public interface ReviewCommandService {

    ReviewResDTO.CreateDTO createReview(Long storeId, ReviewReqDTO.CreateDTO dto);
}
