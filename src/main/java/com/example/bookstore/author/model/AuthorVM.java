package com.example.bookstore.author.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AuthorVM {

    private Long id;

    @NotBlank(message = "Value is demanded! Blank digits are not accepted!")
    @Length(min = 2, message = "Value too short!")
    @Size(max = 32, message = "Value too long!")
    private String name;

    @NotBlank(message = "Value is demanded! Blank digits are not accepted!")
    @Length(min = 2, message = "Value too short!")
    @Size(max = 32, message = "Value too long!")
    private String surname;

    private Long version;
}
