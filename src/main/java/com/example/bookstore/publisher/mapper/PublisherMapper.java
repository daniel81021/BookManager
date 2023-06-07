package com.example.bookstore.publisher.mapper;

import com.example.bookstore.publisher.model.Publisher;
import com.example.bookstore.publisher.model.PublisherJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublisherMapper {

    PublisherMapper MAPPER = Mappers.getMapper(PublisherMapper.class);

    @Mapping(target = "audit", ignore = true)
    PublisherJpa toPublisherJpa(Publisher publisher);

    Publisher toPublisher(PublisherJpa publisherJpa);
}
