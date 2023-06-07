package com.example.bookstore.author.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private Long id;
    private String name;
    private String surname;
    private Long version;
}
