package com.example.bookstore.warehouse.mapper;

import com.example.bookstore.warehouse.model.Warehouse;
import com.example.bookstore.warehouse.model.WarehouseJpa;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface WarehouseMapper {

    @Mapping(target = "audit", ignore = true)
    WarehouseJpa toWarehouseJpa(Warehouse warehouse);
    Warehouse toWarehouse(WarehouseJpa warehouseJpa);
}
