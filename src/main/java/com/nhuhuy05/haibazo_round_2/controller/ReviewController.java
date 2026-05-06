package com.nhuhuy05.haibazo_round_2.controller;

import com.nhuhuy05.haibazo_round_2.dto.request.ReviewRequest;
import com.nhuhuy05.haibazo_round_2.dto.response.ApiResponse;
import com.nhuhuy05.haibazo_round_2.dto.response.Pagination;
import com.nhuhuy05.haibazo_round_2.dto.response.ReviewResponse;
import com.nhuhuy05.haibazo_round_2.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ReviewController {
    ReviewService reviewService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReviewResponse>>> getAllReviews(Pageable pageable) {
        Page<ReviewResponse> page = reviewService.getAllReviews(pageable);
        return ResponseEntity.ok(ApiResponse.<List<ReviewResponse>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get all reviews successfully")
                .data(page.getContent())
                .pagination(Pagination.from(page))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> getReviewById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.<ReviewResponse>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get review successfully")
                .data(reviewService.getReviewById(id))
                .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse>> createReview(@Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<ReviewResponse>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message("Create review successfully")
                .data(reviewService.createReview(request))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> updateReview(@PathVariable Integer id, @Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.ok(ApiResponse.<ReviewResponse>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Update review successfully")
                .data(reviewService.updateReview(id, request))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Delete review successfully")
                .build());
    }
}
