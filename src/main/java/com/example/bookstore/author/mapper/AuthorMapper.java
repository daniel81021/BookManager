package com.example.bookstore.author.mapper;

import com.example.bookstore.author.model.Author;
import com.example.bookstore.author.model.AuthorJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface AuthorMapper {

    AuthorMapper MAPPER = Mappers.getMapper(AuthorMapper.class);

    @Mapping(target = "audit", ignore = true)
    AuthorJpa toAuthorJpa(Author author);


    Author toAuthor(AuthorJpa authorJpa);

    Set<AuthorJpa> toAuthorJpas(Set<Author> authors);

    Set<Author> toAuthors(Set<AuthorJpa> authorJpas);
}
