package com.example.bookstore.repositories;

import com.example.bookstore.model.entities.ProductJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductJpa, Long> {
}
