package com.example.bookstore.author.repository;

import com.example.bookstore.author.model.AuthorJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorJpaRepository extends JpaRepository<AuthorJpa, Long> {


    Optional<AuthorJpa> findFirstByNameIgnoreCaseAndSurnameIgnoreCase(String name, String surname);
}
