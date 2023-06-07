package com.example.bookstore.offer.repository;

import com.example.bookstore.offer.model.OfferJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferJpaRepository extends JpaRepository<OfferJpa, Long> {
}
