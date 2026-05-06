package com.nhuhuy05.haibazo_round_2.service;

import com.nhuhuy05.haibazo_round_2.dto.request.BookRequest;
import com.nhuhuy05.haibazo_round_2.dto.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponse createBook(BookRequest request);
    BookResponse updateBook(Integer id, BookRequest request);
    Page<BookResponse> getAllBooks(Pageable pageable);
    BookResponse getBookById(Integer id);
    void deleteBook(Integer id);

}
