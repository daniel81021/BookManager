package com.example.bookstore.model.entities;

import com.example.bookstore.book.model.BookJpa;
import com.example.bookstore.model.enums.Grade;
import com.example.bookstore.model.enums.State;
import com.example.bookstore.model.utils.CreateUpdateData;
import com.example.bookstore.warehouse.model.WarehouseJpa;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "product")
public class ProductJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_jpa_id")
    private BookJpa bookJpa;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne
    @JoinColumn(name = "warehouse_jpa_id")
    private WarehouseJpa warehouseJpa;

    @Embedded
    private CreateUpdateData createUpdateData = new CreateUpdateData();

    @Version
    private Long version;
}
