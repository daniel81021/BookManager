package com.example.bookstore.warehouse.mapper;

import com.example.bookstore.model.utils.Audit;
import com.example.bookstore.warehouse.model.Warehouse;
import com.example.bookstore.warehouse.model.WarehouseJpa;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(SpringRunner.class)
@SpringBootTest
class WarehouseMapperTest {


    @Autowired
    private WarehouseMapper warehouseMapper;

    private final Long VERSION = 1L;
    private final Long ID = 1L;
    private final Integer NUMBER = 2;
    private final Integer NUMBER_2 = 3;
    private final String DESCRIPTION = "Black, small";
    private final String LOCATION = "First shell, left site";


    @Test
    void toWarehouseJpa() {

        // given
        Warehouse warehouse = createWarehouse(NUMBER, DESCRIPTION, LOCATION);

        // when
        WarehouseJpa warehouseJpa = warehouseMapper.toWarehouseJpa(warehouse);

        // then
        Assertions.assertThat(warehouseJpa).isNotNull();
        Assertions.assertThat(warehouseJpa.getClass()).isEqualTo(WarehouseJpa.class);
        Assertions.assertThat(warehouseJpa) //
                .usingRecursiveComparison() //
                .ignoringFields("audit") //
                .isEqualTo(warehouse);
        Assertions.assertThat(warehouseJpa.getAudit().getCreateDate()).isNull();
        Assertions.assertThat(warehouseJpa.getAudit().getUpdateDate()).isNull();
        Assertions.assertThat(warehouseJpa.getAudit()).isNotNull();
        Assertions.assertThat(warehouseJpa.getCapacity()).isEqualTo(100.0);

    }


    @Test
    void toWarehouse() {

        // given
        WarehouseJpa warehouseJpa = createWarehouseJpa(NUMBER, DESCRIPTION, LOCATION);

        // when
        Warehouse warehouse = warehouseMapper.toWarehouse(warehouseJpa);

        // then
        Assertions.assertThat(warehouse).isNotNull();
        Assertions.assertThat(warehouse.getClass()).isEqualTo(Warehouse.class);
        Assertions.assertThat(warehouse) //
                .usingRecursiveComparison() //
                .ignoringFields("audit") //
                .isEqualTo(warehouseJpa);
        Assertions.assertThat(warehouse.getCapacity()).isEqualTo(100.0);

    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void toWarehouseSeries(Integer number, String description, String location) {

        // given
        WarehouseJpa warehouseJpa = createWarehouseJpa(number, description, location);

        // when
        Warehouse warehouse = warehouseMapper.toWarehouse(warehouseJpa);

        // then
        Assertions.assertThat(warehouse).isNotNull();
        Assertions.assertThat(warehouse.getClass()).isEqualTo(Warehouse.class);
        Assertions.assertThat(warehouse) //
                .usingRecursiveComparison() //
                .ignoringFields("audit") //
                .isEqualTo(warehouseJpa);
        Assertions.assertThat(warehouse.getCapacity()).isEqualTo(100.0);

    }
    private Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(NUMBER, DESCRIPTION, LOCATION),
                Arguments.of(NUMBER_2, DESCRIPTION, LOCATION)
        );
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

    private Audit createAudit() {
        Audit audit = new Audit();
        audit.setCreateDate(LocalDate.of(1999, 1, 1));
        audit.setUpdateDate(LocalDate.of(2000, 1, 1));
        return audit;
    }

    private WarehouseJpa createWarehouseJpa(Integer number, String description, String location) {
        WarehouseJpa warehouseJpa = new WarehouseJpa();
        warehouseJpa.setId(ID);
        warehouseJpa.setNumber(number);
        warehouseJpa.setDescription(description);
        warehouseJpa.setLocation(location);
        warehouseJpa.setVersion(VERSION);
        warehouseJpa.setCapacity(100.0);
        warehouseJpa.setAudit(createAudit());
        return warehouseJpa;
    }
}