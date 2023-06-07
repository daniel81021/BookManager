package com.example.bookstore.publisher.service;

import com.example.bookstore.publisher.model.Publisher;
import org.springframework.stereotype.Component;

@Component
public interface PublisherJpaService {

    Publisher save(Publisher publisher);

    Publisher update(Publisher publisher);
}
