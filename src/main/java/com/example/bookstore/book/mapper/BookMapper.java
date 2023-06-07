package com.example.bookstore.book.mapper;

import com.example.bookstore.author.mapper.AuthorMapper;
import com.example.bookstore.book.model.Book;
import com.example.bookstore.book.model.BookJpa;
import com.example.bookstore.publisher.mapper.PublisherMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(uses = {AuthorMapper.class, PublisherMapper.class})
public interface BookMapper {

    BookMapper MAPPER = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "publisher", source = "publisherJpa")
    Book toBook(BookJpa bookJpa);

    @Mapping(target = "publisherJpa", source = "publisher")
    @Mapping(target = "audit", ignore = true)
    BookJpa toBookJpa(Book book);
}
