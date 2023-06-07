package com.example.bookstore.book.repository;

import com.example.bookstore.author.model.AuthorJpa;
import com.example.bookstore.author.repository.AuthorJpaRepository;
import com.example.bookstore.book.model.BookJpa;
import com.example.bookstore.model.enums.CoverType;
import com.example.bookstore.model.utils.Audit;
import com.example.bookstore.publisher.model.PublisherJpa;
import com.example.bookstore.publisher.repository.PublisherJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookJpaRepositoryTest {

    @Autowired
    private AuthorJpaRepository authorJpaRepository;

    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Autowired
    private PublisherJpaRepository publisherJpaRepository;

    private final String NAME = "Paweł";
    private final String SURNAME = "Majka";
    private final String NAME_2 = "Radosław";
    private final String SURNAME_2 = "Rusak";

    private final String NAME_3 = "Lee";

    private final String SURNAME_3 = "Child";
    private final String TITLE = "Czerwone żniwa, t.1: Uderzenie wyprzedzające";
    private final String TITLE_2 = "Zgodnie z planem";
    private final Long ID = 1L;
    private final String CITY = "Kraków";
    private final String CITY_2 = "Warszawa";

    private final Year YEAR = Year.of(2019);
    private final Year YEAR_2 = Year.of(2020);

    private final String PUBLISHER_NAME = "Znak Horyzont";
    private final String PUBLISHER_NAME_2 = "Albatros";

    private final String ISBN = "9788324057719";
    private final String ISBN_2 = "9788381258449";

    private final Double SIZE = 10.0;
    private final Double SIZE_2 = 11.0;




    @Test
    void saveBook() {

        // given
        AuthorJpa authorJpa = createAuthor(NAME, SURNAME);
        AuthorJpa authorJpa2 = createAuthor(NAME_2, SURNAME_2);

        AuthorJpa saved = authorJpaRepository.save(authorJpa);
        AuthorJpa saved2 = authorJpaRepository.save(authorJpa2);

        Set<AuthorJpa> authors = new HashSet<>();
        authors.add(saved);
        authors.add(saved2);

        PublisherJpa publisherJpa = createPublisher(PUBLISHER_NAME);

        PublisherJpa savedPublisher = publisherJpaRepository.save(publisherJpa);

        BookJpa bookJpa = createBook(TITLE, authors, CITY, YEAR, savedPublisher, CoverType.HARD, ISBN, SIZE);

        // when
        BookJpa savedBook = bookJpaRepository.save(bookJpa);

        // then
        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook.getPublisherJpa().getName()).isEqualTo(savedPublisher.getName());
        Assertions.assertThat(savedBook.getPublisherJpa().getId()).isEqualTo(savedPublisher.getId());
        Assertions.assertThat(savedBook.getAudit().getCreateDate()).isNotNull();
        Assertions.assertThat(savedBook.getAudit().getUpdateDate()).isNull();
        Assertions.assertThat(savedBook.getAuthors().stream().map(AuthorJpa::getName).collect(Collectors.toList())) //
                .containsExactlyInAnyOrder(NAME, NAME_2) //
                .hasSize(2);
        Assertions.assertThat(savedBook.getAuthors().stream().map(AuthorJpa::getId).collect(Collectors.toList())) //
                .containsExactlyInAnyOrder(saved.getId(), saved2.getId()) //
                .hasSize(2);
        Assertions.assertThat(savedBook.getVersion()) //
                .isNotNull() //
                .isEqualTo(0);
        Assertions.assertThat(savedBook.getId()).isNotNull();

    }

    @Test
    void shouldSaveBookWithDefaultDataWhenEmptyValuesProvided(){

        // given
        BookJpa bookJpa = createBookToConstraintsTests(TITLE, null, null, null);

        // when
        BookJpa savedBook = bookJpaRepository.save(bookJpa);

        // then
        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook.getCity()).isEqualTo("Brak miejsca wydania");
        Assertions.assertThat(savedBook.getPublicationYear()).isEqualTo(Year.of(1899));
        Assertions.assertThat(savedBook.getSize()).isEqualTo(1.0);
    }

    @Test
    void shouldThrowExceptionWhenTitleToShort(){

        // given
        BookJpa bookJpa = createBookToConstraintsTests("I", null, null, null);

        // when
        // then
        Assertions.assertThatThrownBy(()->bookJpaRepository.save(bookJpa)) //
                .isInstanceOf(ConstraintViolationException.class) //
                .hasMessageContaining("Value too short!");

    }

    @Test
    void shouldReturnBookByGivenParams(){

        // given
        AuthorJpa authorJpa = createAuthor(NAME, SURNAME);
        AuthorJpa authorJpa2 = createAuthor(NAME_2, SURNAME_2);

        AuthorJpa saved = authorJpaRepository.save(authorJpa);
        AuthorJpa saved2 = authorJpaRepository.save(authorJpa2);

        Set<AuthorJpa> authors = new HashSet<>();
        authors.add(saved);
        authors.add(saved2);

        PublisherJpa publisherJpa = createPublisher(PUBLISHER_NAME);

        PublisherJpa savedPublisher = publisherJpaRepository.save(publisherJpa);

        BookJpa bookJpa = createBook(TITLE, authors, CITY, YEAR, savedPublisher, CoverType.HARD, ISBN, SIZE);

        BookJpa savedBook = bookJpaRepository.save(bookJpa);

        AuthorJpa authorJpa3 = createAuthor(NAME_3, SURNAME_3);

        AuthorJpa saved3 = authorJpaRepository.save(authorJpa3);

        Set<AuthorJpa> authors2 = new HashSet<>();
        authors.add(saved3);

        PublisherJpa publisherJpa2 = createPublisher(PUBLISHER_NAME_2);

        PublisherJpa savedPublisher2 = publisherJpaRepository.save(publisherJpa2);

        BookJpa bookJpa2 = createBook(TITLE_2, authors2, CITY_2, YEAR_2, savedPublisher2, CoverType.PAPERBACK, ISBN_2, SIZE_2);

        BookJpa savedBook2 = bookJpaRepository.save(bookJpa2);

        // when
        Optional<BookJpa> foundBook = bookJpaRepository.findUniqueByParams(TITLE_2, ISBN_2, YEAR_2, CoverType.PAPERBACK);

        // then
        Assertions.assertThat(foundBook).isPresent();


    }
    private AuthorJpa createAuthor(String name, String surname) {
        AuthorJpa authorJpa = new AuthorJpa();
        authorJpa.setName(name);
        authorJpa.setSurname(surname);
        return authorJpa;
    }

    private BookJpa createBook(String title, //
                               Set<AuthorJpa> authors, //
                               String city, //
                               Year year, //
                               PublisherJpa publisher, //
                               CoverType cover, //
                               String isbn, //
                               Double size) {
        BookJpa bookJpa = new BookJpa();
        bookJpa.setTitle(title);
        bookJpa.setAuthors(authors);
        bookJpa.setCity(city);
        bookJpa.setPublicationYear(year);
        bookJpa.setPublisherJpa(publisher);
        bookJpa.setCoverType(cover);
        bookJpa.setIsbn(isbn);
        bookJpa.setSize(size);
        return bookJpa;
    }

    private PublisherJpa createPublisher(String publisherName){
        PublisherJpa publisherJpa = new PublisherJpa();
        publisherJpa.setName(publisherName);
        return publisherJpa;
    }

    private BookJpa createBookToConstraintsTests(String title, //
                                                 String city, //
                                                 Year year, //
                                                 Double size){
        BookJpa bookJpa = new BookJpa();
        bookJpa.setTitle(title);
        bookJpa.setCity(city);
        bookJpa.setPublicationYear(year);
        bookJpa.setSize(size);
        return bookJpa;
    }
}