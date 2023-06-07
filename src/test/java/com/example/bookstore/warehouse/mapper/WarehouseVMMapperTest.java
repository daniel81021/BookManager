package com.example.bookstore.warehouse.mapper;

import com.example.bookstore.warehouse.model.Warehouse;
import com.example.bookstore.warehouse.model.WarehouseVM;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(SpringRunner.class)
@SpringBootTest
class WarehouseVMMapperTest {

    private final Long VERSION = 1L;
    private final Long ID = 1L;
    private final Integer NUMBER = 2;
    private final Integer NUMBER_2 = 3;
    private final String DESCRIPTION = "Black, small";
    private final String DESCRIPTION_2 = "White, small";
    private final String LOCATION = "First shell, left site";
    private final String LOCATION_2 = "First shell, right site";

    @Autowired
    WarehouseVMMapper mapper;

    @ParameterizedTest
    @MethodSource("provideArguments")
    void toWarehouse(Integer number, String description, String location) {

        // given
        WarehouseVM warehouseVM = createWarehouseVM(number, description, location);

        // when
        Warehouse warehouse = mapper.toWarehouse(warehouseVM);

        // then
        Assertions.assertThat(warehouse) //
                .usingRecursiveComparison() //
                .ignoringFields("capacity") //
                .isEqualTo(warehouseVM);

    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void toWarehouseVM(Integer number, String description, String location) {

        // given
        Warehouse warehouse = createWarehouse(number, description, location);

        // when
        WarehouseVM warehouseVM = mapper.toWarehouseVM(warehouse);

        // then
        Assertions.assertThat(warehouseVM) //
                .usingRecursiveComparison() //
                .ignoringFields("capacity") //
                .isEqualTo(warehouse);
    }

    private Warehouse createWarehouse(Integer number, String description, String location) {
        return Warehouse.builder()
                .id(ID)
                .number(number)
                .description(description)
                .capacity(100.0)
                .location(location)
                .version(VERSION)
                .build();
    }

    private WarehouseVM createWarehouseVM(Integer number, String description, String location){
        return WarehouseVM.builder()
                .id(ID)
                .number(number)
                .description(description)
                .location(location)
                .version(VERSION)
                .build();
    }

    private Stream<Arguments> provideArguments(){
        return Stream.of(
                Arguments.of(NUMBER, DESCRIPTION, LOCATION),
                Arguments.of(NUMBER_2, DESCRIPTION_2, LOCATION_2)
        );
    }
}