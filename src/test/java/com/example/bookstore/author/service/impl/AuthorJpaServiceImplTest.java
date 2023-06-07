package com.example.bookstore.author.service.impl;

import com.example.bookstore.author.model.Author;
import com.example.bookstore.author.service.impl.AuthorJpaServiceImpl;
import com.example.bookstore.model.exceptions.InvalidArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AuthorJpaServiceImplTest {

    @Autowired
    AuthorJpaServiceImpl authorJpaService;

    @Test
    void saveAuthor() {

        // given
        Author author = createDTO("Daniel", "Ziola");
        // when
        Author saved = authorJpaService.saveAuthor(author);

        // then
        Assertions.assertThat(saved.getName()).isEqualTo(author.getName());
        Assertions.assertThat(saved.getId()).isNotNull();
        Assertions.assertThat(saved.getVersion()).isNotNull();
    }

    @Test
    void shouldThrowInvalidArgumentExceptionWhenSameAuthorSavedAgain() {
        // given
        Author author = createDTO("Daniel", "Ziola");
        Author author2 = createDTO("Joanna", "Ochocinska");
        Author author3 = createDTO("Robert", "Ziola");

        authorJpaService.saveAuthor(author);
        authorJpaService.saveAuthor(author2);
        authorJpaService.saveAuthor(author3);

        // when
        // then
        Assertions.assertThatThrownBy(() -> authorJpaService.saveAuthor(author))//
                .isInstanceOf(InvalidArgumentException.class)
                .hasMessage("Message");

    }

    private Author createDTO(String name, String surname){
        Author author = new Author();
        author.setName(name);
        author.setSurname(surname);
        return author;
    }
}