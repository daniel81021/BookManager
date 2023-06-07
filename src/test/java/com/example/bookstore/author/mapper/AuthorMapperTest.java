package com.example.bookstore.author.mapper;

import com.example.bookstore.author.mapper.AuthorMapper;
import com.example.bookstore.author.model.Author;
import com.example.bookstore.author.model.AuthorJpa;
import com.example.bookstore.model.utils.Audit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class AuthorMapperTest {

    private final String NAME = "Daniel";
    private final String NAME_2 = "Joanna";
    private final String NAME_3 = "Emil";
    private final String SURNAME = "Ziola";
    private final String SURNAME_2 = "Ochocinska";
    private final String SURNAME_3 = "Karewicz";
    private final Long ID = 1L;
    private final Long ID_2 = 2L;
    private final Long ID_3 = 3L;
    private final Long VERSION = 1L;
    private final Long VERSION_2 = 2L;
    private final Long VERSION_3 = 3L;

    @Test
    void toAuthor() {

        // given
        AuthorJpa authorJpa = createJpa(NAME, SURNAME, ID, VERSION);

        // when
        Author author = AuthorMapper.MAPPER.toAuthor(authorJpa);

        // then
        Assertions.assertThat(author.getName()).isEqualTo(authorJpa.getName());
        Assertions.assertThat(author.getSurname()).isEqualTo(authorJpa.getSurname());
        Assertions.assertThat(author.getId()).isEqualTo(authorJpa.getId());
        Assertions.assertThat(author.getVersion()).isEqualTo(authorJpa.getVersion());
    }

    @Test
    void toAuthorJpa() {

        // given
        Author author = createDTO(NAME, SURNAME, ID, VERSION);

        // when
        AuthorJpa authorJpa = AuthorMapper.MAPPER.toAuthorJpa(author);

        // then
        Assertions.assertThat(authorJpa.getName()).isEqualTo(author.getName());
        Assertions.assertThat(authorJpa.getSurname()).isEqualTo(author.getSurname());
        Assertions.assertThat(authorJpa.getId()).isEqualTo(author.getId());
        Assertions.assertThat(authorJpa.getVersion()).isEqualTo(author.getVersion());
        Assertions.assertThat(authorJpa.getAudit().getCreateDate()).isNull();
        Assertions.assertThat(authorJpa.getAudit().getUpdateDate()).isNull();
    }

    @Test
    void toAuthorJpasSet(){

        // given
        Author author = createDTO(NAME, SURNAME, ID, VERSION);
        Author author2 = createDTO(NAME_2, SURNAME_2, ID_2, VERSION_2);
        Author author3 = createDTO(NAME_3, SURNAME_3, ID_3, VERSION_3);

        Set<Author> authors = new HashSet<>();
        authors.add(author);
        authors.add(author2);
        authors.add(author3);

        // when
        Set<AuthorJpa> authorJpaSet = AuthorMapper.MAPPER.toAuthorJpas(authors);

        // then
        Assertions.assertThat(authorJpaSet.stream().map(AuthorJpa::getName).collect(Collectors.toList())) //
                .hasSize(3) //
                .containsExactlyInAnyOrder(NAME, NAME_2, NAME_3);
        Assertions.assertThat(authorJpaSet.stream().map(AuthorJpa::getId).collect(Collectors.toList())) //
                .containsExactlyInAnyOrder(ID, ID_2, ID_3);
        Assertions.assertThat(authorJpaSet.stream().map(AuthorJpa::getSurname).collect(Collectors.toList())) //
                .containsExactlyInAnyOrder(SURNAME, SURNAME_2, SURNAME_3);
        Assertions.assertThat(authorJpaSet.stream().map(AuthorJpa::getVersion).collect(Collectors.toList())) //
                .containsExactlyInAnyOrder(VERSION, VERSION_2, VERSION_3);
        Assertions.assertThat(authorJpaSet.stream().map(a->a.getAudit().getCreateDate()).collect(Collectors.toList())) //
                .containsOnlyNulls();
        Assertions.assertThat(authorJpaSet.stream().map(a->a.getAudit().getUpdateDate()).collect(Collectors.toList())) //
                .containsOnlyNulls();
        Assertions.assertThat(new ArrayList<>(authorJpaSet).get(0).getClass()).isEqualTo(AuthorJpa.class);
    }

    @Test
    void toAuthorsSet(){

        // given
        AuthorJpa author = createJpa(NAME, SURNAME, ID, VERSION);
        AuthorJpa author2 = createJpa(NAME_2, SURNAME_2, ID_2, VERSION_2);
        AuthorJpa author3 = createJpa(NAME_3, SURNAME_3, ID_3, VERSION_3);

        Set<AuthorJpa> authorJpas = new HashSet<>();
        authorJpas.add(author);
        authorJpas.add(author2);
        authorJpas.add(author3);

        // when
        Set<Author> authorsSet = AuthorMapper.MAPPER.toAuthors(authorJpas);

        // then
        Assertions.assertThat(authorsSet.stream().map(Author::getName).collect(Collectors.toList())) //
                .hasSize(3) //
                .containsExactlyInAnyOrder(NAME, NAME_2, NAME_3);
        Assertions.assertThat(authorsSet.stream().map(Author::getId).collect(Collectors.toList())) //
                .containsExactlyInAnyOrder(ID, ID_2, ID_3);
        Assertions.assertThat(authorsSet.stream().map(Author::getSurname).collect(Collectors.toList())) //
                .containsExactlyInAnyOrder(SURNAME, SURNAME_2, SURNAME_3);
        Assertions.assertThat(authorsSet.stream().map(Author::getVersion).collect(Collectors.toList())) //
                .containsExactlyInAnyOrder(VERSION, VERSION_2, VERSION_3);
        Assertions.assertThat(new ArrayList<>(authorsSet).get(0).getClass()).isEqualTo(Author.class);
    }

    private Audit createAudit(){
        Audit audit = new Audit();
        audit.setCreateDate(LocalDate.of(1999,1,1));
        audit.setUpdateDate(LocalDate.of(2000,1,1));
        return audit;
    }
    private AuthorJpa createJpa(String name, String surname, Long id, Long version){
        AuthorJpa authorJpa = new AuthorJpa();
        authorJpa.setName(name);
        authorJpa.setSurname(surname);
        authorJpa.setId(id);
        authorJpa.setVersion(version);
        authorJpa.setAudit(createAudit());
        return authorJpa;
    }

    private Author createDTO(String name, String surname, Long id, Long version){
        Author author = new Author();
        author.setId(id);
        author.setName(name);
        author.setSurname(surname);
        author.setVersion(version);
        return author;
    }
}