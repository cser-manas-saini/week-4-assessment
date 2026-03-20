package com.capgemini.library.management.service;

import com.capgemini.library.management.entity.Book;
import com.capgemini.library.management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public Book addBook(Book book) {
        if (book.getAvailableCopies() < 0) {
            throw new RuntimeException("Invalid copies");
        }
        book.setBorrowedCopies(0);
        return repository.save(book);
    }

    public Book getBook(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book updateBook(int id, Book updated) {
        Book book = getBook(id);

        book.setTitle(updated.getTitle());
        book.setAuthor(updated.getAuthor());

        if (updated.getAvailableCopies() < 0) {
            throw new RuntimeException("Invalid copies");
        }

        book.setAvailableCopies(updated.getAvailableCopies());

        return repository.save(book);
    }

    public String deleteBook(int id) {
        Book book = getBook(id);
        repository.delete(book);
        return "Book deleted successfully";
    }

    public String borrowBook(int id) {
        Book book = getBook(id);

        if (book.getAvailableCopies() == 0) {
            throw new RuntimeException("No copies available");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        book.setBorrowedCopies(book.getBorrowedCopies() + 1);

        repository.save(book);

        return "Book borrowed successfully";
    }

    public String returnBook(int id) {
        Book book = getBook(id);

        if (book.getBorrowedCopies() == 0) {
            throw new RuntimeException("No borrowed books to return");
        }

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        book.setBorrowedCopies(book.getBorrowedCopies() - 1);

        repository.save(book);

        return "Book returned successfully";
    }
}
