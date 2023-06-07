package com.example.bookstore.book.model;

import com.example.bookstore.author.model.AuthorJpa;
import com.example.bookstore.model.enums.CoverType;
import com.example.bookstore.model.utils.Audit;
import com.example.bookstore.publisher.model.PublisherJpa;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "book")
public class BookJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Value is demanded! Blank digits are not accepted!")
    @Length(min = 2, message = "Value too short!")
    @Size(max = 64, message = "Value too long!")
    private String title;

    @OneToMany
    private Set<AuthorJpa> authors = new HashSet<>();

    @Size(max = 32, message = "Value too long!")
    private String city;

    @Nullable
    private Year publicationYear;

    @Enumerated(EnumType.STRING)
    private CoverType coverType;

    @Size(max = 16, message = "Value too long!")
    private String isbn;

    @Min(1)
    private Double size;

    @ManyToOne
    @JoinColumn(name = "publisher")
    private PublisherJpa publisherJpa;

    @Embedded
    private Audit audit = new Audit();

    @Version
    private Long version;

    @PrePersist
    void prePersist() {
        if (this.city == null || this.city.isBlank() || this.city.isEmpty()) {
            city = "Brak miejsca wydania";
        }
        if (this.publicationYear == null) {
            publicationYear = Year.of(1899);
        }
        if (size == null || size < 1.0) {
            size = 1.0;
        }
    }
}

// TODO: chcę, aby książka o isbn, roku publikacji, typie oprawy i tytule była unikalna i by nie dało jej się zapisać po raz drugi.
