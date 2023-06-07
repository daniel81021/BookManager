package com.example.bookstore.publisher.mapper;

import com.example.bookstore.publisher.model.Publisher;
import com.example.bookstore.publisher.model.PublisherVM;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PublisherVMMapperTest {

    private final Long ID = 1L;
    private final String NAME = "Publisher";
    private final Long VERSION = 1L;

    @Test
    void toPublisher() {

        // given
        PublisherVM publisherVM = createPublisherVM(ID, NAME, VERSION);

        // when
        Publisher publisher = PublisherVMMapper.MAPPER.toPublisher(publisherVM);

        // then
        Assertions.assertThat(publisher).usingRecursiveComparison().isEqualTo(publisherVM);
    }

    @Test
    void toPublisherVM() {

        // given
        Publisher publisher = createPublisher(ID, NAME, VERSION);

        // when
        PublisherVM publisherVM = PublisherVMMapper.MAPPER.toPublisherVM(publisher);

        // then
        Assertions.assertThat(publisherVM).usingRecursiveComparison().isEqualTo(publisher);

    }

    private Publisher createPublisher(Long id, String name, Long version){
        Publisher publisher = new Publisher();
        publisher.setId(id);
        publisher.setName(name);
        publisher.setVersion(version);
        return publisher;
    }

    private PublisherVM createPublisherVM(Long id, String name, Long version){
        PublisherVM publisherVM = new PublisherVM();
        publisherVM.setId(id);
        publisherVM.setName(name);
        publisherVM.setVersion(version);
        return publisherVM;
    }
}