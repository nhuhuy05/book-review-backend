package com.nhuhuy05.haibazo_round_2.service.impl;

import com.nhuhuy05.haibazo_round_2.dto.request.ReviewRequest;
import com.nhuhuy05.haibazo_round_2.dto.response.BookResponse;
import com.nhuhuy05.haibazo_round_2.dto.response.ReviewResponse;
import com.nhuhuy05.haibazo_round_2.entity.Book;
import com.nhuhuy05.haibazo_round_2.entity.Review;
import com.nhuhuy05.haibazo_round_2.mapper.ReviewMapper;
import com.nhuhuy05.haibazo_round_2.repository.AuthorRepository;
import com.nhuhuy05.haibazo_round_2.repository.BookRepository;
import com.nhuhuy05.haibazo_round_2.repository.ReviewRepository;
import com.nhuhuy05.haibazo_round_2.service.ReviewService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor // Lombok: tạo constructor cho final fields // final = bắt buộc
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j // lombok - dung để tạo logger
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository reviewRepository;
    ReviewMapper reviewMapper;
    BookRepository bookRepository;

    @Override
    public ReviewResponse createReview(@RequestBody ReviewRequest request) {
        Book book = bookRepository.findById(Integer.parseInt(request.getBookId())).orElseThrow(() -> new RuntimeException("Book not found"));
        Review review = reviewMapper.toReview(request);
        review.setBook(book);
        Review createdReview = reviewRepository.save(review);
        return reviewMapper.toReviewReponse(createdReview);
    }

    @Override
    public ReviewResponse updateReview(Integer id, @RequestBody ReviewRequest request) {
        Book book = bookRepository.findById(Integer.parseInt(request.getBookId())).orElseThrow(() -> new RuntimeException("Book not found"));
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        review.setBook(book);
        review.setReview(request.getReview());
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toReviewReponse(savedReview);
    }

    @Override
    public ReviewResponse getReviewById(Integer id) {
        return reviewMapper.toReviewReponse(reviewRepository.findById(id).orElseThrow(
                () -> new RuntimeException("review not found")
        ));
    }

    @Override
    public List<ReviewResponse> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::toReviewReponse)
                .toList();
    }

    @Override
    public void deleteReview(Integer id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        reviewRepository.delete(review);
    }
}
