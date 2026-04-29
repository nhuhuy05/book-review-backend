package com.nhuhuy05.haibazo_round_2.service.impl;

import com.nhuhuy05.haibazo_round_2.dto.request.BookRequest;
import com.nhuhuy05.haibazo_round_2.dto.response.BookResponse;
import com.nhuhuy05.haibazo_round_2.entity.Author;
import com.nhuhuy05.haibazo_round_2.entity.Book;
import com.nhuhuy05.haibazo_round_2.mapper.BookMapper;
import com.nhuhuy05.haibazo_round_2.repository.AuthorRepository;
import com.nhuhuy05.haibazo_round_2.repository.BookRepository;
import com.nhuhuy05.haibazo_round_2.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Lombok: tạo constructor cho final fields // final = bắt buộc
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j // lombok - dung để tạo logger
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;
    BookMapper bookMapper;
    AuthorRepository authorRepository;

    @Override
    public BookResponse createBook(BookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId()).orElseThrow(() -> new RuntimeException("Author not found"));
        Book book = bookMapper.toBook(request);
        book.setAuthor(author);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toBookResponse(savedBook);
    }

    @Override
    public BookResponse updateBook(Integer id, BookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId()).orElseThrow(() -> new RuntimeException("Author not found"));
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        bookMapper.updateBook(book, request);
        book.setAuthor(author);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toBookResponse(savedBook);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                // List -> Stream để
                .stream()
                .map(bookMapper::toBookResponse)
                .toList();
    }

    @Override
    public BookResponse getBookById(Integer id) {
        return bookMapper
                .toBookResponse(bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found")));
    }

    @Override
    public void deleteBook(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(book);
    }

}
