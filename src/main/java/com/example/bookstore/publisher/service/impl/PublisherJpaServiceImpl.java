package com.example.bookstore.publisher.service.impl;

import com.example.bookstore.model.exceptions.InvalidArgumentException;
import com.example.bookstore.publisher.model.Publisher;
import com.example.bookstore.publisher.model.PublisherJpa;
import com.example.bookstore.publisher.mapper.PublisherMapper;
import com.example.bookstore.publisher.repository.PublisherJpaRepository;
import com.example.bookstore.publisher.service.PublisherJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PublisherJpaServiceImpl implements PublisherJpaService {

    @Autowired
    PublisherJpaRepository publisherJpaRepository;

    @Override
    public Publisher save(Publisher publisher) {
        Optional<PublisherJpa> check = publisherJpaRepository.findFirstByNameIgnoreCase(publisher.getName());
        if (check.isPresent()) {
            throw new InvalidArgumentException("Message");
        }
        PublisherJpa saved = publisherJpaRepository.save(PublisherMapper.MAPPER.toPublisherJpa(publisher));
        return PublisherMapper.MAPPER.toPublisher(saved);
    }

    @Override
    public Publisher update(Publisher publisher) {
        PublisherJpa updated = publisherJpaRepository.save(PublisherMapper.MAPPER.toPublisherJpa(publisher));
        return PublisherMapper.MAPPER.toPublisher(updated);
    }
}
