package com.nhuhuy05.haibazo_round_2.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhuhuy05.haibazo_round_2.dto.request.AuthorRequest;
import com.nhuhuy05.haibazo_round_2.dto.response.AuthorResponse;
import com.nhuhuy05.haibazo_round_2.entity.Author;
import com.nhuhuy05.haibazo_round_2.exception.ResourceNotFoundException;
import com.nhuhuy05.haibazo_round_2.mapper.AuthorMapper;
import com.nhuhuy05.haibazo_round_2.repository.AuthorRepository;
import com.nhuhuy05.haibazo_round_2.repository.BookRepository;
import com.nhuhuy05.haibazo_round_2.service.AuthorService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthorServiceImpl implements AuthorService {
    AuthorRepository authorRepository;
    AuthorMapper authorMapper;
    BookRepository bookRepository;

    @Override
    @Transactional
    public AuthorResponse createAuthor(AuthorRequest request) {
        Author author = authorMapper.toAuthor(request);
        Author savedAuthor = authorRepository.save(author);
        return toResponseWithCount(savedAuthor);
    }

    @Override
    @Transactional
    public AuthorResponse updateAuthor(Integer id, AuthorRequest request) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        author.setName(request.getName());
        Author savedAuthor = authorRepository.save(author);
        return toResponseWithCount(savedAuthor);
    }

    @Override
    public Page<AuthorResponse> getAllAuthor(Pageable pageable) {
        return authorRepository.findAll(pageable)
                .map(this::toResponseWithCount);
    }

    @Override
    public AuthorResponse getAuthorById(Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        return toResponseWithCount(author);
    }

    @Override
    @Transactional
    public void deleteAuthor(Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        authorRepository.delete(author);
    }

    private AuthorResponse toResponseWithCount(Author author) {
        AuthorResponse response = authorMapper.toAuthorResponse(author);
        response.setBooksCount((int) bookRepository.countByAuthorId(author.getId()));
        return response;
    }
}
