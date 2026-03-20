package com.capgemini.library.management.entity;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String author;
    private int availableCopies;
    private int borrowedCopies;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public int getBorrowedCopies() {
        return borrowedCopies;
    }

    public void setBorrowedCopies(int borrowedCopies) {
        this.borrowedCopies = borrowedCopies;
    }
}