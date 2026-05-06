package com.nhuhuy05.haibazo_round_2.mapper;

import com.nhuhuy05.haibazo_round_2.dto.request.AuthorRequest;
import com.nhuhuy05.haibazo_round_2.dto.response.AuthorResponse;
import com.nhuhuy05.haibazo_round_2.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(AuthorRequest request);

    @Mapping(target = "booksCount", ignore = true)
    AuthorResponse toAuthorResponse(Author author);
}
