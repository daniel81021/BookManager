package com.example.bookstore.book.model;

import com.example.bookstore.author.model.AuthorVM;
import com.example.bookstore.model.enums.CoverType;
import com.example.bookstore.publisher.model.PublisherVM;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@Data
public class BookVM {

    private Long id;

    @NotBlank(message = "Value is demanded! Blank digits are not accepted!")
    @Length(min = 2, message = "Value too short!")
    @Size(max = 64, message = "Value too long!")
    private String title;

    private Set<AuthorVM> authors = new HashSet<>();

    @Size(max = 32, message = "Value too long!")
    private String city;

    private Year publicationYear;

    private CoverType coverType;

    @Size(max = 16, message = "Value too long!")
    private String isbn;

    @Min(1)
    private Double size;

    private PublisherVM publisher;

    private Long version;
}
