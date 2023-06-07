package com.example.bookstore.publisher.mapper;

import com.example.bookstore.publisher.model.Publisher;
import com.example.bookstore.publisher.model.PublisherVM;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublisherVMMapper {

    PublisherVMMapper MAPPER = Mappers.getMapper(PublisherVMMapper.class);

//    @Mapping(target = ".", source = ".")
    Publisher toPublisher(PublisherVM publisherVM);

    PublisherVM toPublisherVM(Publisher publisher);
}
