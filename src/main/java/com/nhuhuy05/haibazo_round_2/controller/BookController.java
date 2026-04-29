package com.nhuhuy05.haibazo_round_2.controller;

import com.nhuhuy05.haibazo_round_2.dto.request.BookRequest;
import com.nhuhuy05.haibazo_round_2.dto.response.BookResponse;
import com.nhuhuy05.haibazo_round_2.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookController {
    BookService bookService;

    @GetMapping
    ResponseEntity<List<BookResponse>> getBooks() {
        System.out.println(bookService.getAllBooks());
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(request))  ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Integer id, @RequestBody BookRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

}
