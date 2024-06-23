package com.asd.asdfasdfasdfa.web;

import com.asd.asdfasdfasdfa.domain.Author;
import com.asd.asdfasdfasdfa.domain.Book;
import com.asd.asdfasdfasdfa.generated.client.CreateBookGraphQLQuery;
import com.asd.asdfasdfasdfa.generated.client.CreateBookProjectionRoot;
import com.asd.asdfasdfasdfa.generated.types.AuthorInput;
import com.asd.asdfasdfasdfa.generated.types.BookInput;
import com.asd.asdfasdfasdfa.service.BookService;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import graphql.ExecutionResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
class BookControllerTest {

    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    @SpyBean
    BookService bookService;

    @MockBean
    ServletWebServerApplicationContext server;

    @Test
    void getBooks() {
        int count = 3;
        int offset = 0;
        String field = "title";
        String query = STR."{getBooks(count: \{count}, offset: \{offset}) { \{field}}}";

        List<String> titles = dgsQueryExecutor.executeAndExtractJsonPath(
                query,
                STR."data.getBooks[*].\{field}"
        );

        String s = bookService.getBooks(3, 0).getFirst().getTitle();

        assertThat(titles).contains(s);
    }

    @Test
    void createBook() {
        Book book = Book.builder()
                .id(1L)
                .title("title")
                .year(1234)
                .author(Author.builder()
                        .id(1L)
                        .firstName("홍")
                        .lastName("길석")
                        .build())
                .build();

        GraphQLQueryRequest graphQLQueryRequest = new GraphQLQueryRequest(
                CreateBookGraphQLQuery.newRequest()
                        .input(BookInput.newBuilder()
                                .id(String.valueOf(1L))
                                .title("title")
                                .year(1234)
                                .author(AuthorInput.newBuilder()
                                        .id(String.valueOf(1L))
                                        .firstName("홍")
                                        .lastName("길석")
                                        .build()
                                ).build())
                        .build(),
                new CreateBookProjectionRoot<>().author().id());

        ExecutionResult executionResult = dgsQueryExecutor.execute(graphQLQueryRequest.serialize());

        assertThat(executionResult.getErrors()).isEmpty();
        verify(bookService).addBook(book);
    }
}