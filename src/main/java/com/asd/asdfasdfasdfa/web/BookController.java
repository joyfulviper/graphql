package com.asd.asdfasdfasdfa.web;

import com.asd.asdfasdfasdfa.domain.Book;
import com.asd.asdfasdfasdfa.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * spring-graphql 방식
 */
@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @QueryMapping
    public List<Book> getBooks(@Argument int count, @Argument int offset) {
        return bookService.getBooks(count, offset);
    }

    @MutationMapping
    public Book createBook(@Argument("input") Book input) {
        return bookService.addBook(input);
    }

    @MutationMapping
    public Book updateBook(@Argument("input") Book input) {
        return bookService.updateBook(input);
    }

    @MutationMapping
    public Boolean deleteBook(@Argument Long id) {
        return bookService.deleteBook(id);
    }

    @ResponseBody
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getRestBooks(
            @RequestParam(defaultValue = "10") int count,
            @RequestParam(defaultValue = "0") int offset
    ) {
        return ResponseEntity.ok(bookService.getBooks(count, offset));
    }
}