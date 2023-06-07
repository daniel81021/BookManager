package com.example.bookstore.book.repository;

import com.example.bookstore.book.model.BookJpa;
import com.example.bookstore.model.enums.CoverType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.Optional;

@Repository
public interface BookJpaRepository extends JpaRepository<BookJpa, Long> {


    /**
     * Default method used as ALIAS for long query,
     * @param title - {@String} book title,
     * @param isbn - {@String} book isbn number,
     * @param publicationYear - {@Year} book publication year,
     * @param coverType - {@CoverType} enum with book cover type,
     * @return first found in DB {@BookJpa} book entity with given params.
     */
    default Optional<BookJpa> findUniqueByParams(String title, String isbn, Year publicationYear, CoverType coverType){
        return findFirstByTitleIgnoreCaseAndIsbnIgnoreCaseAndPublicationYearAndCoverType(title, isbn, publicationYear, coverType);
    }

    Optional<BookJpa> findFirstByTitleIgnoreCaseAndIsbnIgnoreCaseAndPublicationYearAndCoverType(String title, String isbn, Year publicationYear, CoverType coverType);
}
