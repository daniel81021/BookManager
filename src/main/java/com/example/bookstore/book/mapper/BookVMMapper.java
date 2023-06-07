package com.example.bookstore.book.mapper;

import com.example.bookstore.author.mapper.AuthorVMMapper;
import com.example.bookstore.book.model.Book;
import com.example.bookstore.book.model.BookVM;
import com.example.bookstore.publisher.mapper.PublisherVMMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(uses = {AuthorVMMapper.class, PublisherVMMapper.class})
public interface BookVMMapper {

    BookVMMapper MAPPER = Mappers.getMapper(BookVMMapper.class);

    @Mapping(source = ".", target = ".")
    BookVM toBookVM(Book book);

    @InheritInverseConfiguration
    Book toBook(BookVM bookVM);

}
