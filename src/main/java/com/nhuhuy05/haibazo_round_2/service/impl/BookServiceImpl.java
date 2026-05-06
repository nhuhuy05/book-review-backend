package com.nhuhuy05.haibazo_round_2.service.impl;

import com.nhuhuy05.haibazo_round_2.dto.request.BookRequest;
import com.nhuhuy05.haibazo_round_2.dto.response.BookResponse;
import com.nhuhuy05.haibazo_round_2.entity.Author;
import com.nhuhuy05.haibazo_round_2.entity.Book;
import com.nhuhuy05.haibazo_round_2.exception.ResourceNotFoundException;
import com.nhuhuy05.haibazo_round_2.mapper.BookMapper;
import com.nhuhuy05.haibazo_round_2.repository.AuthorRepository;
import com.nhuhuy05.haibazo_round_2.repository.BookRepository;
import com.nhuhuy05.haibazo_round_2.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;
    BookMapper bookMapper;
    AuthorRepository authorRepository;

    @Override
    @Transactional
    public BookResponse createBook(BookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + request.getAuthorId() + " not found"));
        Book book = bookMapper.toBook(request);
        book.setAuthor(author);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toBookResponse(savedBook);
    }

    @Override
    @Transactional
    public BookResponse updateBook(Integer id, BookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + request.getAuthorId() + " not found"));
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        bookMapper.updateBook(book, request);
        book.setAuthor(author);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toBookResponse(savedBook);
    }

    @Override
    public Page<BookResponse> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toBookResponse);
    }

    @Override
    public BookResponse getBookById(Integer id) {
        return bookMapper
                .toBookResponse(bookRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found")));
    }

    @Override
    @Transactional
    public void deleteBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        bookRepository.delete(book);
    }
}
