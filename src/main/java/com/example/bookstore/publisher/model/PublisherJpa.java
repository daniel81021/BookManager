package com.example.bookstore.publisher.model;

import com.example.bookstore.model.utils.Audit;
import com.example.bookstore.model.utils.CreateUpdateData;
import lombok.*;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "publisher")
public class PublisherJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Value is demanded! Blank digits are not accepted!")
    @Length(min = 2, message = "Value too short!")
    @Size(max = 32, message = "Value too long!")
    private String name;

    @Version
    private Long version;

    @Embedded
    private Audit audit = new Audit();
}
