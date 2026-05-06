package com.nhuhuy05.haibazo_round_2.service.impl;

import com.nhuhuy05.haibazo_round_2.dto.request.ReviewRequest;
import com.nhuhuy05.haibazo_round_2.dto.response.ReviewResponse;
import com.nhuhuy05.haibazo_round_2.entity.Book;
import com.nhuhuy05.haibazo_round_2.entity.Review;
import com.nhuhuy05.haibazo_round_2.exception.ResourceNotFoundException;
import com.nhuhuy05.haibazo_round_2.mapper.ReviewMapper;
import com.nhuhuy05.haibazo_round_2.repository.BookRepository;
import com.nhuhuy05.haibazo_round_2.repository.ReviewRepository;
import com.nhuhuy05.haibazo_round_2.service.ReviewService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor // Lombok: tạo constructor cho final fields // final = bắt buộc
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j // lombok - dung để tạo logger
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository reviewRepository;
    ReviewMapper reviewMapper;
    BookRepository bookRepository;

    @Override
    @Transactional
    public ReviewResponse createReview(ReviewRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + request.getBookId() + " not found"));
        Review review = reviewMapper.toReview(request);
        review.setBook(book);
        Review createdReview = reviewRepository.save(review);
        return reviewMapper.toReviewResponse(createdReview);
    }

    @Override
    @Transactional
    public ReviewResponse updateReview(Integer id, ReviewRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + request.getBookId() + " not found"));
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review with id " + id + " not found"));
        review.setBook(book);
        review.setReview(request.getReview());
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toReviewResponse(savedReview);
    }

    @Override
    public ReviewResponse getReviewById(Integer id) {
        return reviewMapper.toReviewResponse(reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review with id " + id + " not found")));
    }

    @Override
    public Page<ReviewResponse> getAllReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable)
                .map(reviewMapper::toReviewResponse);
    }

    @Override
    @Transactional
    public void deleteReview(Integer id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review with id " + id + " not found"));
        reviewRepository.delete(review);
    }
}
