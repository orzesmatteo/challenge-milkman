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

@SpringBootTest(classes = ChallengeApplication.class)
@ActiveProfiles("h2")
public class ChallengeMappersTests {

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
        Address address = new Address("Viale Ferri", "13", "47833", "Morciano di Romagna", "Rimini");
        AddressDto addressDto = new AddressDto("Viale Ferri", "13", "47833", "Morciano di Romagna", "Rimini");
        AddressDto generatedDto = addressMapper.daoToDto(address);
        Address generatedDao = addressMapper.dtoToDao(addressDto);
        assert (generatedDto.equals(addressDto));
        assert (generatedDao.equals(address));
    }

    @Test
    void verifyCoordinatesMapper() {
        Coordinates coordinates = new Coordinates(1.0, 2.0);
        CoordinatesDto coordinatesDto = new CoordinatesDto(1.0, 2.0);
        CoordinatesDto generatedDto = coordinatesMapper.daoToDto(coordinates);
        Coordinates generatedDao = coordinatesMapper.dtoToDao(coordinatesDto);
        assert (generatedDto.equals(coordinatesDto));
        assert (generatedDao.equals(coordinates));
    }

    @Test
    void verifyDepotMapper() {
        UUID randomUUID = UUID.randomUUID();
        Instant instant = Instant.now();
        Depot depot = new Depot("name", new Address("Viale Ferri", "13", "47833", "Morciano di Romagna", "Rimini"), new Coordinates(1.0, 2.0));
        depot.setId(randomUUID);
        depot.setCreation(instant);
        depot.setLastUpdate(instant);
        DepotDto depotDto = new DepotDto(randomUUID, instant, instant, "name", new AddressDto("Viale Ferri", "13", "47833", "Morciano di Romagna", "Rimini"), new CoordinatesDto(1.0, 2.0));
        DepotDto generatedDto = depotMapper.daoToDto(depot);
        Depot generatedDao = depotMapper.dtoToDao(depotDto);
        assert (generatedDto.equals(depotDto));
        assert (generatedDao.equals(depot));
    }

    @Test
    void verifyOrderMapper() {
        UUID randomUUID = UUID.randomUUID();
        Instant instant = Instant.now();
        Depot depot = new Depot("name", new Address("Viale Ferri", "13", "47833", "Morciano di Romagna", "Rimini"), new Coordinates(1.0, 2.0));
        depot.setId(randomUUID);
        depot.setCreation(instant);
        depot.setLastUpdate(instant);
        Supplier supplier = new Supplier("name");
        supplier.setId(randomUUID);
        supplier.setCreation(instant);
        supplier.setLastUpdate(instant);
        Package packageDao = new Package(new Address("Viale Ferri", "13", "47833", "Morciano di Romagna", "Rimini"), new Coordinates(1.0, 2.0), null, "notes", PackageStatus.IN_DELIVERY, instant);
        packageDao.setId(randomUUID);
        packageDao.setCreation(instant);
        packageDao.setLastUpdate(instant);
        DepotDto depotDto = new DepotDto(randomUUID, instant, instant, "name", new AddressDto("Viale Ferri", "13", "47833", "Morciano di Romagna", "Rimini"), new CoordinatesDto(1.0, 2.0));
        Order order = new Order(supplier, depot, "notes", Set.of(packageDao), OrderStatus.IN_DELIVERY, instant, instant, instant);
        order.setId(randomUUID);
        order.setCreation(instant);
        order.setLastUpdate(instant);
        OrderDto orderDto = new OrderDto(randomUUID, instant, instant, new SupplierDto(randomUUID, instant, instant, "name"), depotDto, "notes", Set.of(new PackageDto(randomUUID, instant, instant, new AddressDto("Viale Ferri", "13", "47833", "Morciano di Romagna", "Rimini"), new CoordinatesDto(1.0, 2.0), "notes", PackageStatusDto.IN_DELIVERY, instant)),
                OrderStatusDto.IN_DELIVERY, instant, instant, instant);
        OrderDto generatedDto = orderMapper.daoToDto(order);
        Order generatedDao = orderMapper.dtoToDao(orderDto);
        assert (generatedDto.equals(orderDto));
        assert (generatedDao.equals(order));
    }

    @Test
    void verifyPackageMapper() {
        UUID randomUUID = UUID.randomUUID();
        Instant instant = Instant.now();
        Package packageDao = new Package(new Address("Viale Ferri", "13", "47833", "Morciano di Romagna", "Rimini"), new Coordinates(1.0, 2.0), null, "notes", PackageStatus.IN_DELIVERY, instant);
        packageDao.setId(randomUUID);
        packageDao.setCreation(instant);
        packageDao.setLastUpdate(instant);
        PackageDto packageDto = new PackageDto(randomUUID, instant, instant, new AddressDto("Viale Ferri", "13", "47833", "Morciano di Romagna", "Rimini"), new CoordinatesDto(1.0, 2.0), "notes", PackageStatusDto.IN_DELIVERY, instant);
        PackageDto generatedDto = packageMapper.daoToDto(packageDao);
        Package generatedDao = packageMapper.dtoToDao(packageDto);
        assert (generatedDto.equals(packageDto));
        assert (generatedDao.equals(packageDao));
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
        assert (generatedDto.equals(supplierDto));
        assert (generatedDao.equals(supplier));
    }

}
