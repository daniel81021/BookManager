package com.example.bookstore.warehouse.model;

import com.example.bookstore.model.utils.Audit;
import com.example.bookstore.model.utils.CreateUpdateData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "warehouse")
public class WarehouseJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    private Integer number;

    private String description;

    private Double capacity;

    private String location;

    @Embedded
    private Audit audit = new Audit();

    @Version
    private Long version;

}
