package com.nhuhuy05.haibazo_round_2.repository;

import com.nhuhuy05.haibazo_round_2.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    long countByAuthorId(Integer authorId);
}
