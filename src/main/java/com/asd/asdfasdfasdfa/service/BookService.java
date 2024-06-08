package com.asd.asdfasdfasdfa.service;

import com.asd.asdfasdfasdfa.domain.Author;
import com.asd.asdfasdfasdfa.domain.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    public static final List<Author> AUTHOR = List.of(new Author(1L, "Joanne", "Rowling", new ArrayList<>()));

    private static final List<Book> BOOKS = new ArrayList<>(Arrays.asList(
            new Book(1L, "Philosopher's Stone", AUTHOR.get(0), 1997),
            new Book(2L, "Goblet of Fire", AUTHOR.get(0), 2000),
            new Book(3L, "Deathly Hallows", AUTHOR.get(0), 2007)
    ));

    public List<Book> getBooks(int count , int offset) {
        return BOOKS.subList(offset, offset + count);
    }

    public Book addBook(Book book) {
        BOOKS.add(book);
        return book;
    }

    public boolean deleteBook(Long id) {
        return BOOKS.removeIf(book -> book.getId().equals(id));
    }

    public Book updateBook(Book book) {
        BOOKS.removeIf(b -> b.getId().equals(book.getId()));
        BOOKS.add(book);
        return book;
    }
}
