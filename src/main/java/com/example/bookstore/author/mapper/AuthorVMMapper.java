package com.example.bookstore.author.mapper;

import com.example.bookstore.author.model.Author;
import com.example.bookstore.author.model.AuthorVM;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorVMMapper {

    AuthorVMMapper MAPPER = Mappers.getMapper(AuthorVMMapper.class);

//    @Mapping(source = ".", target = ".")
    AuthorVM toAuthorVM(Author author);

//    @InheritInverseConfiguration
    Author toAuthor(AuthorVM authorVM);
}
