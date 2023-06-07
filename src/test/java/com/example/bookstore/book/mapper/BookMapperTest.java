package com.example.bookstore.book.mapper;

import com.example.bookstore.author.model.Author;
import com.example.bookstore.author.model.AuthorJpa;
import com.example.bookstore.book.mapper.BookMapper;
import com.example.bookstore.book.model.Book;
import com.example.bookstore.book.model.BookJpa;
import com.example.bookstore.model.enums.CoverType;
import com.example.bookstore.publisher.model.Publisher;
import com.example.bookstore.publisher.model.PublisherJpa;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class BookMapperTest {

    private final Long ID = 1L;
    private final String PUBLISHER_NAME = "Publisher";
    private final Long VERSION = 1L;
    private final String NAME = "Daniel";
    private final String NAME_2 = "Joanna";
    private final String NAME_3 = "Emil";
    private final String SURNAME = "Ziola";
    private final String SURNAME_2 = "Ochocinska";
    private final String SURNAME_3 = "Karewicz";
    private final Long ID_2 = 2L;
    private final Long ID_3 = 3L;
    private final Long VERSION_2 = 2L;
    private final Long VERSION_3 = 3L;
    private final String CITY = "Miasto";
    private final CoverType COVER = CoverType.HARD;

    @Test
    void toBookJpa() {
        // given
        Author author = createAuthor(NAME, SURNAME);
        Author author2 = createAuthor(NAME_2, SURNAME_2);
        Author author3 = createAuthor(NAME_3, SURNAME_3);

        Set<Author> authors = new HashSet<>();
        authors.add(author);
        authors.add(author2);
        authors.add(author3);

        Publisher publisher = createPublisher(PUBLISHER_NAME);

        Book book = createBook(authors, CITY, COVER, publisher);

        // when
        BookJpa bookJpa = BookMapper.MAPPER.toBookJpa(book);

        // then
        Assertions.assertThat(bookJpa.getClass()).isEqualTo(BookJpa.class);
        Assertions.assertThat(new ArrayList<>(bookJpa.getAuthors()).get(0).getClass()).isEqualTo(AuthorJpa.class);
        Assertions.assertThat(bookJpa.getPublisherJpa().getClass()).isEqualTo(PublisherJpa.class);
        Assertions.assertThat(bookJpa.getAuthors().stream().map(AuthorJpa::getName).collect(Collectors.toList())) //
                .hasSize(3) //
                .containsExactlyInAnyOrder(NAME, NAME_2, NAME_3);

    }

    @Test
    void toBook() {
    }

    private Book createBook(Set<Author> authors, String city, CoverType cover, Publisher publisher){
        Book book = new Book();
        book.setAuthors(authors);
        book.setCity(city);
        book.setCoverType(cover);
        book.setPublisher(publisher);
        return book;
    }

    private Publisher createPublisher(String name){
        Publisher publisher = new Publisher();
        publisher.setName(name);
        return publisher;
    }

    private Author createAuthor(String name, String surname){
        Author author = new Author();
        author.setName(name);
        author.setSurname(surname);
        return author;
    }
}