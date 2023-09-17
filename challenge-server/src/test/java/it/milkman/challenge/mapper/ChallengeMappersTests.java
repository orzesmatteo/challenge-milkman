package it.milkman.challenge.mapper;

import it.milkman.challenge.dao.Depot;
import it.milkman.challenge.dao.Order;
import it.milkman.challenge.dao.Package;
import it.milkman.challenge.dao.Supplier;
import it.milkman.challenge.dao.embeddables.Address;
import it.milkman.challenge.dao.embeddables.Coordinates;
import it.milkman.challenge.dao.enums.OrderStatus;
import it.milkman.challenge.dao.enums.PackageStatus;
import it.milkman.challenge.dto.AddressDto;
import it.milkman.challenge.dto.CoordinatesDto;
import it.milkman.challenge.dto.depot.DepotDto;
import it.milkman.challenge.dto.order.OrderDto;
import it.milkman.challenge.dto.order.OrderStatusDto;
import it.milkman.challenge.dto.packages.PackageDto;
import it.milkman.challenge.dto.packages.PackageStatusDto;
import it.milkman.challenge.dto.supplier.SupplierDto;
import it.milkman.challenge.server.ChallengeApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import static it.milkman.challenge.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ChallengeApplication.class)
@ActiveProfiles("h2")
class ChallengeMappersTests {

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    CoordinatesMapper coordinatesMapper;

    @Autowired
    DepotMapper depotMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    PackageMapper packageMapper;

    @Autowired
    SupplierMapper supplierMapper;

    @Test
    void verifyAddressMapper() {
        Address address = getAddressForTests();
        AddressDto addressDto = getAddressDtoForTests();
        AddressDto generatedDto = addressMapper.daoToDto(address);
        Address generatedDao = addressMapper.dtoToDao(addressDto);
        assertEquals(generatedDto, addressDto);
        assertEquals(generatedDao, address);
    }

    @Test
    void verifyCoordinatesMapper() {
        Coordinates coordinates = getCoordinatesForTests();
        CoordinatesDto coordinatesDto = getCoordinatesDtoForTests();
        CoordinatesDto generatedDto = coordinatesMapper.daoToDto(coordinates);
        Coordinates generatedDao = coordinatesMapper.dtoToDao(coordinatesDto);
        assertEquals(generatedDto, coordinatesDto);
        assertEquals(generatedDao, coordinates);
    }

    @Test
    void verifyDepotMapper() {
        UUID randomUUID = UUID.randomUUID();
        Instant instant = Instant.now();
        Depot depot = new Depot("name", getAddressForTests(), getCoordinatesForTests());
        depot.setId(randomUUID);
        depot.setCreation(instant);
        depot.setLastUpdate(instant);
        DepotDto depotDto = new DepotDto(randomUUID, instant, instant, "name", getAddressDtoForTests(), getCoordinatesDtoForTests());
        DepotDto generatedDto = depotMapper.daoToDto(depot);
        Depot generatedDao = depotMapper.dtoToDao(depotDto);
        assertEquals(generatedDto, depotDto);
        assertEquals(generatedDao, depot);
    }

    @Test
    void verifyOrderMapper() {
        UUID randomUUID = UUID.randomUUID();
        Instant instant = Instant.now();
        Depot depot = new Depot("name", getAddressForTests(), getCoordinatesForTests());
        depot.setId(randomUUID);
        depot.setCreation(instant);
        depot.setLastUpdate(instant);
        Supplier supplier = new Supplier("name");
        supplier.setId(randomUUID);
        supplier.setCreation(instant);
        supplier.setLastUpdate(instant);
        Package packageDao = new Package(getAddressForTests(), getCoordinatesForTests(), "notes", PackageStatus.IN_DELIVERY, instant);
        packageDao.setId(randomUUID);
        packageDao.setCreation(instant);
        packageDao.setLastUpdate(instant);
        DepotDto depotDto = new DepotDto(randomUUID, instant, instant, "name", getAddressDtoForTests(), getCoordinatesDtoForTests());
        Order order = new Order(supplier, depot, "notes", Set.of(packageDao), OrderStatus.IN_DELIVERY, instant, instant, instant);
        order.setId(randomUUID);
        order.setCreation(instant);
        order.setLastUpdate(instant);
        OrderDto orderDto = new OrderDto(randomUUID, instant, instant,
                new SupplierDto(randomUUID, instant, instant, "name"), depotDto, "notes",
                Set.of(new PackageDto(randomUUID, instant, instant, getAddressDtoForTests(), getCoordinatesDtoForTests(), "notes", PackageStatusDto.IN_DELIVERY, instant)),
                OrderStatusDto.IN_DELIVERY, instant, instant, instant);
        OrderDto generatedDto = orderMapper.daoToDto(order);
        Order generatedDao = orderMapper.dtoToDao(orderDto);
        assertEquals(generatedDto, orderDto);
        assertEquals(generatedDao, order);
    }

    @Test
    void verifyPackageMapper() {
        UUID randomUUID = UUID.randomUUID();
        Instant instant = Instant.now();
        Package packageDao = new Package(getAddressForTests(), getCoordinatesForTests(), "notes", PackageStatus.IN_DELIVERY, instant);
        packageDao.setId(randomUUID);
        packageDao.setCreation(instant);
        packageDao.setLastUpdate(instant);
        PackageDto packageDto = new PackageDto(randomUUID, instant, instant, getAddressDtoForTests(), getCoordinatesDtoForTests(), "notes", PackageStatusDto.IN_DELIVERY, instant);
        PackageDto generatedDto = packageMapper.daoToDto(packageDao);
        Package generatedDao = packageMapper.dtoToDao(packageDto);
        assertEquals(generatedDto, packageDto);
        assertEquals(generatedDao, packageDao);
    }

    @Test
    void verifySupplierMapper() {
        UUID randomUUID = UUID.randomUUID();
        Instant instant = Instant.now();
        Supplier supplier = new Supplier("name");
        supplier.setId(randomUUID);
        supplier.setCreation(instant);
        supplier.setLastUpdate(instant);
        SupplierDto supplierDto = new SupplierDto(randomUUID, instant, instant, "name");
        SupplierDto generatedDto = supplierMapper.daoToDto(supplier);
        Supplier generatedDao = supplierMapper.dtoToDao(supplierDto);
        assertEquals(generatedDto, supplierDto);
        assertEquals(generatedDao, supplier);
    }

}
