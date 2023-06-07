package com.example.bookstore.book.service.impl;

import com.example.bookstore.author.model.Author;
import com.example.bookstore.author.service.AuthorJpaService;
import com.example.bookstore.book.model.Book;
import com.example.bookstore.book.service.BookJpaService;
import com.example.bookstore.model.enums.CoverType;
import com.example.bookstore.model.exceptions.InvalidArgumentException;
import com.example.bookstore.publisher.model.Publisher;
import com.example.bookstore.publisher.service.PublisherJpaService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookJpaServiceImplTest {

    @Autowired
    AuthorJpaService authorJpaService;

    @Autowired
    PublisherJpaService publisherJpaService;

    @Autowired
    BookJpaService bookJpaService;

    @Test
    void saveBook() {
        // given
        Author author = createAuthor("Daniel", "Ziola");
        Author author2 = createAuthor("Krzysztof", "Ziola");

        Author saved = authorJpaService.saveAuthor(author);
        Author saved2 = authorJpaService.saveAuthor(author2);

        Set<Author> authors = new HashSet<>();
        authors.add(saved);
        authors.add(saved2);

        Publisher publisher = createPublisher("Rebis");

        Publisher savedPublisher = publisherJpaService.save(publisher);

        Book book = createBook(null, "Tytuł", authors, "Poznań", Year.of(1999), savedPublisher, "97889123", null);

        // when
        Book savedBook = bookJpaService.saveBook(book);

        // then
        Assertions.assertThat(saved.getId()).isNotNull();
        Assertions.assertThat(saved2.getId()).isNotNull();
        Assertions.assertThat(savedPublisher.getId()).isNotNull();
        Assertions.assertThat(savedBook.getId()).isNotNull();
        Assertions.assertThat(savedBook.getAuthors().stream().map(Author::getId).collect(Collectors.toList()))
                .containsExactlyInAnyOrder(saved.getId(), saved2.getId());
        Assertions.assertThat(savedBook.getAuthors().stream().map(Author::getName).collect(Collectors.toList()))
                .containsExactlyInAnyOrder(saved.getName(), saved2.getName());
        Assertions.assertThat(savedBook.getPublisher().getId()).isNotNull();
        Assertions.assertThat(savedBook.getSize()).isEqualTo(1.0);
    }


    @ParameterizedTest
    @MethodSource("provideArguments")
    void shouldThrowExceptionWhenIdOrVersionIsNotNull(Long id, Long version) {
        // given
        Author author = createAuthor("Daniel", "Ziola");
        Author author2 = createAuthor("Krzysztof", "Ziola");

        Author saved = authorJpaService.saveAuthor(author);
        Author saved2 = authorJpaService.saveAuthor(author2);

        Set<Author> authors = new HashSet<>();
        authors.add(saved);
        authors.add(saved2);

        Publisher publisher = createPublisher("Rebis");

        Publisher savedPublisher = publisherJpaService.save(publisher);

        Book book = createBook(id, "Tytuł", authors, "Poznań", Year.of(1999), savedPublisher, "97889123", version);

        // when
        // then
        Assertions.assertThatThrownBy(() -> bookJpaService.saveBook(book))
                .isInstanceOf(InvalidArgumentException.class)
                .hasMessage("Book to save can not have Id!");

    }

    @Test
    void shouldThrowExceptionWhenBookIsNotUnique() {
        // given
        Author author = createAuthor("Daniel", "Ziola");
        Author author2 = createAuthor("Krzysztof", "Ziola");

        Author saved = authorJpaService.saveAuthor(author);
        Author saved2 = authorJpaService.saveAuthor(author2);

        Set<Author> authors = new HashSet<>();
        authors.add(saved);
        authors.add(saved2);

        Publisher publisher = createPublisher("Rebis");

        Publisher savedPublisher = publisherJpaService.save(publisher);

        Book book = createBook(null, "Tytuł", authors, "Poznań", Year.of(1999), savedPublisher, "97889123", null);

        bookJpaService.saveBook(book);

        // when
        // then
        Assertions.assertThatThrownBy(() -> bookJpaService.saveBook(book))
                .isInstanceOf(InvalidArgumentException.class)
                .hasMessage("Book already exists in DB");

    }

    private Publisher createPublisher(String name) {
        return Publisher.builder()
                .name(name)
                .build();
    }

    private Author createAuthor(String name, String surname) {
        return Author.builder()
                .name(name)
                .surname(surname)
                .build();
    }

    private Book createBook(Long id, String title, Set<Author> authors, String city, Year year, Publisher publisher, String isbn, Long version) {
        return Book.builder()
                .id(id)
                .title(title)
                .authors(authors)
                .city(city)
                .publicationYear(year)
                .publisher(publisher)
                .coverType(CoverType.HARD)
                .isbn(isbn)
                .version(version)
                .build();

    }

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(1L, null),
                Arguments.of(null, 1L)
        );
    }
}