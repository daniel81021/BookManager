package com.example.bookstore.offer.repository;

import com.example.bookstore.book.model.BookJpa;
import com.example.bookstore.model.entities.OfferJpa;
import com.example.bookstore.model.entities.ProductJpa;
import com.example.bookstore.warehouse.model.WarehouseJpa;
import com.example.bookstore.model.enums.Marketplace;
import com.example.bookstore.book.repository.BookJpaRepository;
import com.example.bookstore.repositories.OfferJpaRepository;
import com.example.bookstore.repositories.ProductJpaRepository;
import com.example.bookstore.repositories.WarehouseJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OfferJpaRepositoryTest {

    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Autowired
    private WarehouseJpaRepository warehouseJpaRepository;

    @Autowired
    private OfferJpaRepository offerJpaRepository;

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Test
    void saveOffer(){

        // given
        BookJpa bookJpa = new BookJpa();
        bookJpa.setTitle("Test");

        BookJpa savedBook = bookJpaRepository.save(bookJpa);

        WarehouseJpa warehouseJpa = new WarehouseJpa();
        warehouseJpa.setDescription("Pierwszy");

        WarehouseJpa savedWarehouse = warehouseJpaRepository.save(warehouseJpa);

        ProductJpa productJpa = new ProductJpa();
        productJpa.setBookJpa(savedBook);
        productJpa.setWarehouseJpa(savedWarehouse);

        ProductJpa savedProduct = productJpaRepository.save(productJpa);

        OfferJpa offerJpa = new OfferJpa();
        offerJpa.setMarketplace(Marketplace.ALLEGRO);
        offerJpa.setProductJpa(savedProduct);

        offerJpaRepository.save(offerJpa);

        OfferJpa offerJpa2 = new OfferJpa();
        offerJpa2.setMarketplace(Marketplace.OLX);
        offerJpa2.setProductJpa(savedProduct);

        offerJpaRepository.save(offerJpa);

        // when
        List<OfferJpa> offers = offerJpaRepository.findAll();
        Optional<WarehouseJpa> warehouseJpaFromDb = warehouseJpaRepository.findById(savedWarehouse.getId());

        // then
        Assertions.assertThat(offers).isNotEmpty();
        Assertions.assertThat(warehouseJpaFromDb).isNotEmpty();
    }


}