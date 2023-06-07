package com.example.bookstore.warehouse.mapper;

import com.example.bookstore.warehouse.model.Warehouse;
import com.example.bookstore.warehouse.model.WarehouseVM;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface WarehouseVMMapper {

    @Mapping(target = "capacity", ignore = true)
    Warehouse toWarehouse(WarehouseVM warehouseVM);
    WarehouseVM toWarehouseVM(Warehouse warehouse);
}
