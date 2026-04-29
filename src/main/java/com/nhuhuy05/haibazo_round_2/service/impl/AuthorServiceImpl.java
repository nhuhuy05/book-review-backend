package com.nhuhuy05.haibazo_round_2.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nhuhuy05.haibazo_round_2.dto.request.AuthorRequest;
import com.nhuhuy05.haibazo_round_2.dto.response.AuthorResponse;
import com.nhuhuy05.haibazo_round_2.entity.Author;
import com.nhuhuy05.haibazo_round_2.mapper.AuthorMapper;
import com.nhuhuy05.haibazo_round_2.repository.AuthorRepository;
import com.nhuhuy05.haibazo_round_2.service.AuthorService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthorServiceImpl implements AuthorService {
    AuthorRepository authorRepository;
    AuthorMapper authorMapper;

    @Override
    public AuthorResponse createAuthor(AuthorRequest request) {
        Author author = authorMapper.toAuthor(request);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.toAuthorResponse(savedAuthor);
    }

    @Override
    public AuthorResponse updateAuthor(Integer id, AuthorRequest request) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("author not found"));
        author.setName(request.getName());
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.toAuthorResponse(savedAuthor);
    }

    @Override
    public List<AuthorResponse> getAllAuthor() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toAuthorResponse)
                .toList();
    }

    @Override
    public AuthorResponse getAuthorById(Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("author not found"));
        return authorMapper.toAuthorResponse(author);
    }

    @Override
    public void deleteAuthor(Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("author not found"));
        authorRepository.delete(author);
    }
}