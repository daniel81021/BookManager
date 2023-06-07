package com.example.bookstore.author.mapper;

import com.example.bookstore.author.mapper.AuthorVMMapper;
import com.example.bookstore.author.model.Author;
import com.example.bookstore.author.model.AuthorVM;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AuthorVMMapperTest {

    private final String NAME = "Daniel";
    private final String SURNAME = "Ziola";
    private final Long ID = 1L;
    private final Long VERSION = 1L;

    @Test
    void toAuthorVM() {

        // given
        Author author = createDTO(ID, NAME, SURNAME, VERSION);

        // when
        AuthorVM authorVM = AuthorVMMapper.MAPPER.toAuthorVM(author);

        // then
        Assertions.assertThat(authorVM).usingRecursiveComparison().isEqualTo(author);


    }

    @Test
    void toAuthor() {

        // given
        AuthorVM authorVM = createAuthorVMInput(ID, NAME, SURNAME, VERSION);

        // when
        Author author = AuthorVMMapper.MAPPER.toAuthor(authorVM);

        // then
        Assertions.assertThat(author).usingRecursiveComparison().isEqualTo(authorVM);

    }

    private AuthorVM createAuthorVMInput(Long id, String name, String surname, Long version){
        AuthorVM authorVM = new AuthorVM();
        authorVM.setId(id);
        authorVM.setName(name);
        authorVM.setSurname(surname);
        authorVM.setVersion(version);
        return authorVM;
    }

    private Author createDTO(Long id, String name, String surname, Long version){
        Author author = new Author();
        author.setId(id);
        author.setName(name);
        author.setSurname(surname);
        author.setVersion(version);
        return author;
    }
}