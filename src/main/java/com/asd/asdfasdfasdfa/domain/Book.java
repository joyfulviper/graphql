package com.asd.asdfasdfasdfa.domain;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Book {
    @Positive
    @Digits(integer = 5, fraction = 0)
    Long id;
    @NotBlank
    String title;
    @NotNull
    Author author;
    @Positive
    @Digits(integer = 5, fraction = 0)
    int year;
}