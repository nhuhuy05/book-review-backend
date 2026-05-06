package com.nhuhuy05.haibazo_round_2.controller;

import com.nhuhuy05.haibazo_round_2.dto.request.BookRequest;
import com.nhuhuy05.haibazo_round_2.dto.response.ApiResponse;
import com.nhuhuy05.haibazo_round_2.dto.response.BookResponse;
import com.nhuhuy05.haibazo_round_2.dto.response.Pagination;
import com.nhuhuy05.haibazo_round_2.service.BookService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookController {
    BookService bookService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookResponse>>> getBooks(Pageable pageable) {
        Page<BookResponse> page = bookService.getAllBooks(pageable);
        return ResponseEntity.ok(ApiResponse.<List<BookResponse>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get all books successfully")
                .data(page.getContent())
                .pagination(Pagination.from(page))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponse>> getBookById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.<BookResponse>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get book successfully")
                .data(bookService.getBookById(id))
                .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BookResponse>> createBook(@Valid @RequestBody BookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<BookResponse>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message("Create book successfully")
                .data(bookService.createBook(request))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponse>> updateBook(@PathVariable Integer id, @Valid @RequestBody BookRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.<BookResponse>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Update book successfully")
                .data(bookService.updateBook(id, request))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Delete book successfully")
                .build());
    }
}
