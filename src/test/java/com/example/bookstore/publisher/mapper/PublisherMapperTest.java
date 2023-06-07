package com.example.bookstore.publisher.mapper;

import com.example.bookstore.publisher.mapper.PublisherMapper;
import com.example.bookstore.publisher.model.Publisher;
import com.example.bookstore.publisher.model.PublisherJpa;
import com.example.bookstore.model.utils.Audit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class PublisherMapperTest {

    private final Long ID = 1L;
    private final String NAME = "Publisher";
    private final Long VERSION = 1L;

    @Test
    void toPublisherJpa() {

        // given
        Publisher publisher = createPublisher(ID, NAME, VERSION);

        // when
        PublisherJpa publisherJpa = PublisherMapper.MAPPER.toPublisherJpa(publisher);

        // then
        Assertions.assertThat(publisherJpa.getId()).isEqualTo(publisher.getId());
        Assertions.assertThat(publisherJpa.getName()).isEqualTo(publisher.getName());
        Assertions.assertThat(publisherJpa.getVersion()).isEqualTo(publisher.getVersion());
        Assertions.assertThat(publisherJpa.getAudit().getCreateDate()).isNull();
        Assertions.assertThat(publisherJpa.getAudit().getUpdateDate()).isNull();
    }

    @Test
    void toPublisher() {

        // given
        PublisherJpa publisherJpa = createPublisherJpa(ID, NAME, VERSION);

        // when
        Publisher publisher = PublisherMapper.MAPPER.toPublisher(publisherJpa);

        // then
        Assertions.assertThat(publisher.getId()).isEqualTo(publisherJpa.getId());
        Assertions.assertThat(publisher.getName()).isEqualTo(publisherJpa.getName());
        Assertions.assertThat(publisher.getVersion()).isEqualTo(publisherJpa.getVersion());
    }

    private Audit createAudit(){
        Audit audit = new Audit();
        audit.setCreateDate(LocalDate.of(1999,1,1));
        audit.setUpdateDate(LocalDate.of(2000,1,1));
        return audit;
    }
    private Publisher createPublisher(Long id, String name, Long version){
        Publisher publisher = new Publisher();
        publisher.setId(id);
        publisher.setName(name);
        publisher.setVersion(version);
        return publisher;
    }

    private PublisherJpa createPublisherJpa(Long id, String name, Long version){
        PublisherJpa publisherJpa = new PublisherJpa();
        publisherJpa.setId(id);
        publisherJpa.setName(name);
        publisherJpa.setVersion(version);
        publisherJpa.setAudit(createAudit());
        return publisherJpa;
    }
}