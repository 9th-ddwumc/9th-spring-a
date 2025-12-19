package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ReviewCommandService {
    ReviewResDTO.newReview addNewReview(ReviewReqDTO.newReview request, MultipartFile reviewPicture);

    void deleteReviewImage(Long reviewId);
}
