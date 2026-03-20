package com.capgemini.library.management.controller;

import com.capgemini.library.management.entity.Book;
import com.capgemini.library.management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        return service.getBook(id);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {
        return service.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        return service.deleteBook(id);
    }

    @PostMapping("/borrow/{id}")
    public Map<String, String> borrow(@PathVariable int id) {
        return Map.of("message", service.borrowBook(id));
    }

    @PostMapping("/return/{id}")
    public Map<String, String> returnBook(@PathVariable int id) {
        return Map.of("message", service.returnBook(id));
    }
}
