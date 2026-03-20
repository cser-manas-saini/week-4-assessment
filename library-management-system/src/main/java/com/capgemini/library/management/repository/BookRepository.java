package com.capgemini.library.management.repository;

import com.capgemini.library.management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}