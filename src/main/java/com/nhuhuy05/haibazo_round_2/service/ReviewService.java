package com.nhuhuy05.haibazo_round_2.service;

import com.nhuhuy05.haibazo_round_2.dto.request.ReviewRequest;
import com.nhuhuy05.haibazo_round_2.dto.response.ReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    ReviewResponse createReview(ReviewRequest request);
    ReviewResponse updateReview(Integer id, ReviewRequest request);
    ReviewResponse getReviewById(Integer id);
    Page<ReviewResponse> getAllReviews(Pageable pageable);
    void deleteReview(Integer id);

}
