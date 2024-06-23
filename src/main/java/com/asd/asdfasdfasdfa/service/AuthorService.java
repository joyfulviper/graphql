package com.asd.asdfasdfasdfa.service;

import com.asd.asdfasdfasdfa.domain.Author;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

import static com.asd.asdfasdfasdfa.service.BookService.AUTHOR;


/**
 * 넷플릭스 방식 @link https://github.com/Netflix/dgs-examples-java
 */
@DgsComponent
public class AuthorService {

    @DgsMutation
    public Author createAuthor(@InputArgument(name = "input") Author author) {
        AUTHOR.add(author);
        return author;
    }

    @DgsMutation
    public Author updateAuthor(@InputArgument(name = "input") Author author) {
        AUTHOR.removeIf(a -> a.getId().equals(author.getId()));
        AUTHOR.add(author);
        return author;
    }

    @DgsMutation
    public Boolean deleteAuthor(@InputArgument Long id) {
        return AUTHOR.removeIf(author -> author.getId().equals(id));
    }

    @DgsQuery
    public List<Author> getAuthors(@InputArgument Integer count, @InputArgument Integer offset) {
        return AUTHOR.subList(offset, offset + count);
    }

}
