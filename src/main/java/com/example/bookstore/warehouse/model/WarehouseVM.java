package com.example.bookstore.warehouse.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WarehouseVM {

    private Long id;
    private Integer number;
    private String description;
    private String location;
    private Long version;
}
