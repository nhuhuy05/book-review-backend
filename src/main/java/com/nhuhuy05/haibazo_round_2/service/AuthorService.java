package com.nhuhuy05.haibazo_round_2.service;

import com.nhuhuy05.haibazo_round_2.dto.request.AuthorRequest;
import com.nhuhuy05.haibazo_round_2.dto.response.AuthorResponse;

import java.util.List;

public interface AuthorService {
    AuthorResponse createAuthor(AuthorRequest request);

    AuthorResponse updateAuthor(Integer id, AuthorRequest request);

    List<AuthorResponse> getAllAuthor();

    AuthorResponse getAuthorById(Integer id);

    void deleteAuthor(Integer id);

}
