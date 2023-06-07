package com.example.bookstore.offer.model;

import com.example.bookstore.model.entities.ProductJpa;
import com.example.bookstore.model.enums.Marketplace;
import com.example.bookstore.model.utils.CreateUpdateData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "offer")
public class OfferJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Marketplace marketplace;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "product_jpa_id")
    private ProductJpa productJpa;

    @Embedded
    private CreateUpdateData createUpdateData = new CreateUpdateData();

    @Version
    private Long version;


}
