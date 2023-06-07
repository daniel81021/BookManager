package com.example.bookstore.book.service.impl;

import com.example.bookstore.book.mapper.BookMapper;
import com.example.bookstore.book.model.Book;
import com.example.bookstore.book.model.BookJpa;
import com.example.bookstore.book.repository.BookJpaRepository;
import com.example.bookstore.book.service.BookJpaService;
import com.example.bookstore.model.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class BookJpaServiceImpl implements BookJpaService {
    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Override
    public Book saveBook(Book book) {
        if (book.getId() != null || book.getVersion() != null) {
            throw new InvalidArgumentException("Book to save can not have Id!");
        }
        Optional<BookJpa> unique = bookJpaRepository.findUniqueByParams(book.getTitle(), book.getIsbn(), book.getPublicationYear(), book.getCoverType());
        if(unique.isPresent()){
            throw new InvalidArgumentException("Book already exists in DB");
        }
        BookJpa savedBook = bookJpaRepository.save(BookMapper.MAPPER.toBookJpa(book));
        return BookMapper.MAPPER.toBook(savedBook);
    }
}
