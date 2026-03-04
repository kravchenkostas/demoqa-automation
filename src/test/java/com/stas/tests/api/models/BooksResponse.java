package com.stas.tests.api.models;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BooksResponse {
    private List<Book> books;
    private String userId;
    private int booksCount;
}
