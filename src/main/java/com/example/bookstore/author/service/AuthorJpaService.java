package com.example.bookstore.author.service;

import com.example.bookstore.author.model.Author;


public interface AuthorJpaService {

    Author saveAuthor(Author author);

    Author updateAuthor(Author author);
}
