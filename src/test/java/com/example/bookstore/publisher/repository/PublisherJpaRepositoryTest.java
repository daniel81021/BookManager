package com.example.bookstore.publisher.repository;

import com.example.bookstore.publisher.model.PublisherJpa;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PublisherJpaRepositoryTest {

    @Autowired
    PublisherJpaRepository publisherJpaRepository;

    private final String NAME = "Albatros";
    private final String NAME_2 = "PWN";
    private final String NAME_3 = "Ossollineum";

    @Test
    void findFirstByNameIgnoreCase() {

        // given
        PublisherJpa publisher = createPublisher(NAME);
        PublisherJpa publisher2 = createPublisher(NAME_2);
        PublisherJpa publisher3 = createPublisher(NAME_3);

        publisherJpaRepository.save(publisher);
        PublisherJpa saved = publisherJpaRepository.save(publisher2);
        publisherJpaRepository.save(publisher3);

        // when
        Optional<PublisherJpa> publisherJpa = publisherJpaRepository.findFirstByNameIgnoreCase(NAME_2);

        // then
        Assertions.assertThat(publisherJpa).isPresent();
        Assertions.assertThat(publisherJpa.get().getId()).isEqualTo(saved.getId());
        Assertions.assertThat(publisherJpa.get().getName()).isEqualTo(NAME_2);
        Assertions.assertThat(publisherJpa.get().getAudit().getCreateDate()).isNotNull();
        Assertions.assertThat(publisherJpa.get().getAudit().getUpdateDate()).isNull();
    }

    @Test
    void shouldReturnOptionalEmpty() {

        // given
        PublisherJpa publisher = createPublisher(NAME);
        PublisherJpa publisher2 = createPublisher(NAME_3);

        publisherJpaRepository.save(publisher);
        publisherJpaRepository.save(publisher2);

        // when
        Optional<PublisherJpa> publisherJpa = publisherJpaRepository.findFirstByNameIgnoreCase(NAME_2);

        // then
        Assertions.assertThat(publisherJpa).isEmpty();
    }

    @ParameterizedTest
    @CsvSource({"    , Value is demanded! Blank digits are not accepted!", //
            "I, Value too short!", //
            "32digitsistoolongforthisfield!!!!! , Value too long!"})
    void shouldThrowExceptionWhenNameIsIncorrect(String name, String expectedMessage) {

        // given
        PublisherJpa publisher = createPublisher(name);

        // when
        // then
        Assertions.assertThatThrownBy(() -> publisherJpaRepository.save(publisher)) //
                .isInstanceOf(ConstraintViolationException.class) //
                .hasMessageContaining(expectedMessage);
    }

    private PublisherJpa createPublisher(String name) {
        PublisherJpa publisherJpa = new PublisherJpa();
        publisherJpa.setName(name);
        return publisherJpa;
    }
}