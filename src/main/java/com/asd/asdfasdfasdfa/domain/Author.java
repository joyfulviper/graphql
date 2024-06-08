package com.asd.asdfasdfasdfa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class Author{
    Long id;
    String firstName;
    String lastName;
    List<Book> books;
}