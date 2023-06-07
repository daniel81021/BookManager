package com.example.bookstore.publisher.repository;

import com.example.bookstore.publisher.model.PublisherJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherJpaRepository extends JpaRepository<PublisherJpa, Long> {

    Optional<PublisherJpa> findFirstByNameIgnoreCase(String name);
}
