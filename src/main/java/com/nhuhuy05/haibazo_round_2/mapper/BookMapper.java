package com.nhuhuy05.haibazo_round_2.mapper;

import com.nhuhuy05.haibazo_round_2.dto.request.BookRequest;
import com.nhuhuy05.haibazo_round_2.dto.response.BookResponse;
import com.nhuhuy05.haibazo_round_2.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {
    // map request -> entity
    Book toBook(BookRequest request);

    // entity -> response
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "authorName", source = "author.name")
    BookResponse toBookResponse(Book book);

    // map du lieu tu request -> book
    void updateBook(@MappingTarget Book book, BookRequest request);
}
