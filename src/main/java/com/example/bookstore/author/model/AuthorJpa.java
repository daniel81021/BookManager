package com.example.bookstore.author.model;

import com.example.bookstore.model.utils.Audit;
import com.example.bookstore.model.utils.CreateUpdateData;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "author")
public class AuthorJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Value is demanded! Blank digits are not accepted!")
    @Length(min = 2, message = "Value too short!")
    @Size(max = 16, message = "Value too long!")
    private String name;

    @NotBlank(message = "Value is demanded! Blank digits are not accepted!")
    @Length(min = 2, message = "Value too short!")
    @Size(max = 16, message = "Value too long!")
    private String surname;

    @Version
    private Long version;

    @Embedded
    private Audit audit = new Audit();

}

//TODO: chcę mieć możliwość utworzenia Autora. Imię min. 2 znaki, Nazwisko też min 2 znaki. Nie mogą być puste lub same spacje.
// imię + nazwisko mają być wyjątkowe w bazie - nie może być 2 autorów o imieniu i nazwisku Adam Mickiewicz.
// Wiadomości z Konstrainta trzeba zrobić jako stałe + może podawać wartości?
