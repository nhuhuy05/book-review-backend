package com.nhuhuy05.haibazo_round_2.service;

import com.nhuhuy05.haibazo_round_2.dto.request.BookRequest;
import com.nhuhuy05.haibazo_round_2.dto.request.ReviewRequest;
import com.nhuhuy05.haibazo_round_2.dto.response.BookResponse;
import com.nhuhuy05.haibazo_round_2.dto.response.ReviewResponse;

import java.util.List;

public interface ReviewService {
    ReviewResponse createReview(ReviewRequest request);
    ReviewResponse updateReview(Integer id, ReviewRequest request);
    ReviewResponse getReviewById(Integer id);
    List<ReviewResponse> getAllReviews();
    void deleteReview(Integer id);

}
