package com.example.bookstore.warehouse.model;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class Warehouse {

    private Long id;
    private Integer number;
    private String description;
    private Double capacity;
    private String location;
    private Long version;
}
