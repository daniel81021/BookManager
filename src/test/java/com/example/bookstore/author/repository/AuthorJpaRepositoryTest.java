package com.example.bookstore.author.repository;

import com.example.bookstore.author.model.AuthorJpa;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AuthorJpaRepositoryTest {

    private final String NAME_DANIEL = "Daniel";
    private final String NAME_KRZYSZTOF = "Krzysztof";
    private final String NAME_RAFAL = "Rafal";
    private final String NAME_ROBERT = "Robert";
    private final String NAME_JOANNA = "Joanna";
    private final String SURNAME_ZIOLA = "Ziola";
    private final String SURNAME_OCHOCINSKA = "Ochocinska";

    @Autowired
    private AuthorJpaRepository authorJpaRepository;

    @Test
    void saveAuthor() {

        // given
        AuthorJpa author = createAuthor(NAME_DANIEL, SURNAME_ZIOLA);
        AuthorJpa author2 = createAuthor(NAME_KRZYSZTOF, SURNAME_ZIOLA);

        // when
        AuthorJpa saved = authorJpaRepository.save(author);
        AuthorJpa saved2 = authorJpaRepository.save(author2);

        // then
        Assertions.assertThat(saved.getName()).isEqualTo(NAME_DANIEL);

        Assertions.assertThat(saved2.getId()).isEqualTo(2L);
    }

    @Test
    void findAll() {

        // given
        AuthorJpa author = createAuthor(NAME_DANIEL, SURNAME_ZIOLA);
        AuthorJpa author2 = createAuthor(NAME_KRZYSZTOF, SURNAME_ZIOLA);
        AuthorJpa author3 = createAuthor(NAME_RAFAL, SURNAME_ZIOLA);
        AuthorJpa author4 = createAuthor(NAME_ROBERT, SURNAME_ZIOLA);
        AuthorJpa author5 = createAuthor(NAME_JOANNA, SURNAME_OCHOCINSKA);

        // when
        AuthorJpa savedAuthor = authorJpaRepository.save(author);
        AuthorJpa savedAuthor2 = authorJpaRepository.save(author2);
        AuthorJpa savedAuthor3 = authorJpaRepository.save(author3);
        AuthorJpa savedAuthor4 = authorJpaRepository.save(author4);
        AuthorJpa savedAuthor5 = authorJpaRepository.save(author5);

        List<AuthorJpa> authors = authorJpaRepository.findAll();

        // then
        Assertions.assertThat(savedAuthor.getId()).isEqualTo(1L);
        Assertions.assertThat(savedAuthor2.getId()).isEqualTo(2L);
        Assertions.assertThat(savedAuthor3.getId()).isEqualTo(3L);
        Assertions.assertThat(savedAuthor4.getId()).isEqualTo(4L);
        Assertions.assertThat(savedAuthor5.getId()).isEqualTo(5L);

        Assertions.assertThat(authors).hasSize(5);
        Assertions.assertThat(authors.stream().map(AuthorJpa::getName).collect(Collectors.toList()))
                .containsAll(List.of(NAME_DANIEL, NAME_JOANNA, NAME_KRZYSZTOF, NAME_RAFAL, NAME_ROBERT));
        Assertions.assertThat(authors.stream().map(AuthorJpa::getSurname).collect(Collectors.toList()))
                .containsAll(List.of(SURNAME_ZIOLA, SURNAME_OCHOCINSKA));

    }

    @Test
    void test(){
        // given
        AuthorJpa authorJpa = createAuthor("Daniel", "Ziola");
        AuthorJpa authorJpa2 = createAuthor("Joanna", "Ochocińska");
        AuthorJpa authorJpa3 = createAuthor("Zbyszko", "Górczak");
        AuthorJpa authorJpa4 = createAuthor("Daniel", "Ziola");

        AuthorJpa first = authorJpaRepository.save(authorJpa);
        authorJpaRepository.save(authorJpa2);
        authorJpaRepository.save(authorJpa3);
        authorJpaRepository.save(authorJpa4);

        // when
        Optional<AuthorJpa> saved = authorJpaRepository.findFirstByNameIgnoreCaseAndSurnameIgnoreCase("Daniel", "Ziola");

        // then
        Assertions.assertThat(saved).isNotEmpty();
        Assertions.assertThat(saved.get()).isNotNull();
        Assertions.assertThat(saved.get().getName()).isEqualTo("Daniel");
        Assertions.assertThat(saved.get().getSurname()).isEqualTo("Ziola");
        Assertions.assertThat(saved.get().getId()).isEqualTo(first.getId());
    }

    @Test
    void test2(){
        // given
        AuthorJpa authorJpa = createAuthor("daniel", "ziola");
        AuthorJpa authorJpa2 = createAuthor("Joanna", "Ochocińska");
        AuthorJpa authorJpa3 = createAuthor("Zbyszko", "Górczak");
        AuthorJpa authorJpa4 = createAuthor("Daniel", "Ziola");

        AuthorJpa first = authorJpaRepository.save(authorJpa);
        authorJpaRepository.save(authorJpa2);
        authorJpaRepository.save(authorJpa3);
        authorJpaRepository.save(authorJpa4);

        // when
        Optional<AuthorJpa> saved = authorJpaRepository.findFirstByNameIgnoreCaseAndSurnameIgnoreCase("Daniel", "Ziola");

        // then
        Assertions.assertThat(saved).isNotEmpty();
        Assertions.assertThat(saved.get()).isNotNull();
        Assertions.assertThat(saved.get().getName()).isEqualTo("daniel");
        Assertions.assertThat(saved.get().getSurname()).isEqualTo("ziola");
        Assertions.assertThat(saved.get().getId()).isEqualTo(first.getId());
    }

    @Test
    void update(){

        // given
        AuthorJpa authorJpa = createAuthor("Daniel", "Ziola");
        AuthorJpa savedAuthor = authorJpaRepository.save(authorJpa);

        AuthorJpa authorJpa2 = createAuthor("Krzysztof", "Ziola");
        AuthorJpa savedAuthor2 = authorJpaRepository.save(authorJpa2);

        Assertions.assertThat(savedAuthor.getId()).isNotEqualTo(savedAuthor2.getId());
        // when
        savedAuthor2.setName("Robert");
        AuthorJpa updated = authorJpaRepository.save(savedAuthor2);

        // then
        Assertions.assertThat(updated.getName()).isEqualTo("Robert");
        Assertions.assertThat(updated.getId()).isEqualTo(savedAuthor2.getId());
        Assertions.assertThat(updated.getId()).isNotEqualTo(savedAuthor.getId());
    }

    private AuthorJpa createAuthor(String name, String surname) {
        AuthorJpa authorJpa = new AuthorJpa();
        authorJpa.setName(name);
        authorJpa.setSurname(surname);
        return authorJpa;
    }


}