package com.nhuhuy05.haibazo_round_2.service;

import com.nhuhuy05.haibazo_round_2.dto.request.BookRequest;
import com.nhuhuy05.haibazo_round_2.dto.response.BookResponse;

import java.util.List;

public interface BookService {
    BookResponse createBook(BookRequest request);
    BookResponse updateBook(Integer id, BookRequest request);
    List<BookResponse> getAllBooks();
    BookResponse getBookById(Integer id);
    void deleteBook(Integer id);

}
