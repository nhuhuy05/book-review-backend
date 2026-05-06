package com.nhuhuy05.haibazo_round_2.service;

import com.nhuhuy05.haibazo_round_2.dto.request.AuthorRequest;
import com.nhuhuy05.haibazo_round_2.dto.response.AuthorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {
    AuthorResponse createAuthor(AuthorRequest request);

    AuthorResponse updateAuthor(Integer id, AuthorRequest request);

    Page<AuthorResponse> getAllAuthor(Pageable pageable);

    AuthorResponse getAuthorById(Integer id);

    void deleteAuthor(Integer id);

}
