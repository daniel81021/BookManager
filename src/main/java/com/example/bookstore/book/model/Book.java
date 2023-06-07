package com.example.bookstore.book.model;

import com.example.bookstore.author.model.Author;
import com.example.bookstore.model.enums.CoverType;
import com.example.bookstore.publisher.model.Publisher;
import com.example.bookstore.publisher.model.PublisherJpa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Long id;

    private String title;

    private Set<Author> authors;

    private String city;

    private Year publicationYear;

    private CoverType coverType;

    private String isbn;

    private Double size;

    private Publisher publisher;

    private Long version;
}
