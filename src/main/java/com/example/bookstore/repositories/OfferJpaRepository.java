package com.example.bookstore.repositories;

import com.example.bookstore.model.entities.OfferJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferJpaRepository extends JpaRepository<OfferJpa, Long> {
}
