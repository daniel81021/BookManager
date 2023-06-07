package com.example.bookstore.repositories;

import com.example.bookstore.warehouse.model.WarehouseJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseJpaRepository extends JpaRepository<WarehouseJpa, Long> {
}
