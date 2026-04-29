package com.nhuhuy05.haibazo_round_2.mapper;

import com.nhuhuy05.haibazo_round_2.dto.request.ReviewRequest;
import com.nhuhuy05.haibazo_round_2.dto.response.ReviewResponse;
import com.nhuhuy05.haibazo_round_2.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review toReview(ReviewRequest request);

    @Mapping(target = "bookName", source = "book.name")
    @Mapping(target = "authorName", source = "book.author.name")
    ReviewResponse toReviewReponse(Review review);
}
