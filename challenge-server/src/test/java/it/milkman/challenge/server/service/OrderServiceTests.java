package it.milkman.challenge.server.service;

import it.milkman.challenge.dao.Order;
import it.milkman.challenge.dao.Supplier;
import it.milkman.challenge.dto.CoordinatesDto;
import it.milkman.challenge.dto.order.CreateOrderDto;
import it.milkman.challenge.dto.order.EditOrderDto;
import it.milkman.challenge.dto.order.OrderDto;
import it.milkman.challenge.dto.order.OrderStatusDto;
import it.milkman.challenge.dto.packages.CreatePackageDto;
import it.milkman.challenge.repository.DepotRepository;
import it.milkman.challenge.repository.OrderRepository;
import it.milkman.challenge.repository.PackageRepository;
import it.milkman.challenge.repository.SupplierRepository;
import it.milkman.challenge.server.ChallengeApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static it.milkman.challenge.TestHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ChallengeApplication.class)
@ActiveProfiles("h2")
class OrderServiceTests {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DepotRepository depotRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    PackageRepository packageRepository;

    @AfterEach
    void cleanup() {
        orderRepository.deleteAll();
        depotRepository.deleteAll();
        supplierRepository.deleteAll();
        packageRepository.deleteAll();
    }

    @Test
    void acceptOrder() {
        UUID depotId = depotRepository.save(getFirstDepotForTests()).getId();
        UUID supplierId = supplierRepository.save(new Supplier("supplierName")).getId();
        CreatePackageDto firstPackage = new CreatePackageDto(getAddressDtoForTests(), new CoordinatesDto(1.0, 4.0), "Notes first package");
        CreatePackageDto secondPackage = new CreatePackageDto(getAddressDtoForTests(), new CoordinatesDto(2.0, 3.0), "Notes second package");
        CreateOrderDto createOrderDto = new CreateOrderDto(depotId, supplierId, Set.of(firstPackage, secondPackage), "Notes");
        UUID orderId = orderService.acceptOrder(createOrderDto);
        Optional<Order> order = orderRepository.findByIdFull(orderId);
        assertTrue(order.isPresent());
        assertEquals(order.get().getPackages().size(), createOrderDto.packages().size());
        assertEquals(order.get().getDepot().getId(), createOrderDto.depotId());
        assertEquals(order.get().getSupplier().getId(), createOrderDto.supplierId());
    }

    @Test
    void editOrder() {
        UUID depotId = depotRepository.save(getFirstDepotForTests()).getId();
        UUID supplierId = supplierRepository.save(new Supplier("supplierName1")).getId();
        CreatePackageDto firstPackage = new CreatePackageDto(getAddressDtoForTests(), new CoordinatesDto(1.0, 4.0), "Notes first package");
        CreatePackageDto secondPackage = new CreatePackageDto(getAddressDtoForTests(), new CoordinatesDto(2.0, 3.0), "Notes second package");
        CreateOrderDto createOrderDto = new CreateOrderDto(depotId, supplierId, Set.of(firstPackage, secondPackage), "Notes");
        UUID orderId = orderService.acceptOrder(createOrderDto);
        Optional<Order> order = orderRepository.findByIdFull(orderId);
        assertTrue(order.isPresent());
        UUID newDepotId = depotRepository.save(getSecondDepotForTests()).getId();
        UUID newSupplierId = supplierRepository.save(new Supplier("supplierName2")).getId();
        String newNotes = "Notes after edit";
        EditOrderDto editOrderDto = new EditOrderDto(order.get().getId(), newDepotId, newSupplierId, newNotes);
        OrderDto editedOrder = orderService.editOrder(order.get().getId(), editOrderDto);
        assertEquals(editedOrder.notes(), editOrderDto.notes());
        assertEquals(editedOrder.depot().id(), editOrderDto.depotId());
        assertEquals(editedOrder.supplier().id(), editOrderDto.supplierId());
    }

    @Test
    void searchOrders() {
        UUID firstDepotId = depotRepository.save(getFirstDepotForTests()).getId();
        UUID secondDepotId = depotRepository.save(getSecondDepotForTests()).getId();
        UUID firstSupplierId = supplierRepository.save(new Supplier("first supplier")).getId();
        UUID secondSupplierId = supplierRepository.save(new Supplier("second supplier")).getId();
        CreatePackageDto firstPackage = new CreatePackageDto(getAddressDtoForTests(), new CoordinatesDto(1.0, 4.0), "Notes first package");
        CreatePackageDto secondPackage = new CreatePackageDto(getAddressDtoForTests(), new CoordinatesDto(2.0, 3.0), "Notes second package");
        CreatePackageDto thirdPackage = new CreatePackageDto(getAddressDtoForTests(), new CoordinatesDto(3.0, 2.0), "Notes third package");
        CreatePackageDto fourthPackage = new CreatePackageDto(getAddressDtoForTests(), new CoordinatesDto(4.0, 1.0), "Notes fourth package");
        CreateOrderDto createFirstOrderDto = new CreateOrderDto(firstDepotId, firstSupplierId, Set.of(firstPackage, secondPackage), "Notes first order");
        UUID firstOrderId = orderService.acceptOrder(createFirstOrderDto);
        CreateOrderDto createSecondOrderDto = new CreateOrderDto(firstDepotId, secondSupplierId, Set.of(thirdPackage), "Notes second order");
        UUID secondOrderId = orderService.acceptOrder(createSecondOrderDto);
        CreateOrderDto createThirdOrderDto = new CreateOrderDto(secondDepotId, secondSupplierId, Set.of(fourthPackage), "Notes third order");
        UUID thirdOrderId = orderService.acceptOrder(createThirdOrderDto);
        orderService.startPlanningOrders(secondDepotId);
        Page<OrderDto> firstSearch = orderService.searchOrders(OrderStatusDto.WAITING, firstDepotId, PageRequest.of(0, 3));
        assertThat(firstSearch.getContent().stream().map(OrderDto::id)).containsExactlyInAnyOrderElementsOf(Set.of(firstOrderId, secondOrderId));
        Page<OrderDto> secondSearch = orderService.searchOrders(OrderStatusDto.WAITING, secondDepotId, PageRequest.of(0, 3));
        assertThat(secondSearch.getContent().stream().map(OrderDto::id)).isEmpty();
        Page<OrderDto> thirdSearch = orderService.searchOrders(OrderStatusDto.STARTED, firstDepotId, PageRequest.of(0, 3));
        assertThat(thirdSearch.getContent().stream().map(OrderDto::id)).isEmpty();
        Page<OrderDto> fourthSearch = orderService.searchOrders(OrderStatusDto.STARTED, secondDepotId, PageRequest.of(0, 3));
        assertThat(fourthSearch.getContent().stream().map(OrderDto::id)).containsExactlyInAnyOrderElementsOf(Set.of(thirdOrderId));
    }

    @Test
    void startPlanningOrders() {
        UUID depotId = depotRepository.save(getFirstDepotForTests()).getId();
        UUID supplierId = supplierRepository.save(new Supplier("supplierName")).getId();
        CreatePackageDto firstPackage = new CreatePackageDto(getAddressDtoForTests(), new CoordinatesDto(1.0, 4.0), "Notes first package");
        CreatePackageDto secondPackage = new CreatePackageDto(getAddressDtoForTests(), new CoordinatesDto(2.0, 3.0), "Notes second package");
        CreatePackageDto thirdPackage = new CreatePackageDto(getAddressDtoForTests(), new CoordinatesDto(3.0, 2.0), "Notes third package");
        CreatePackageDto fourthPackage = new CreatePackageDto(getAddressDtoForTests(), new CoordinatesDto(4.0, 1.0), "Notes fourth package");
        CreateOrderDto createFirstOrderDto = new CreateOrderDto(depotId, supplierId, Set.of(firstPackage, secondPackage), "Notes for first order");
        CreateOrderDto createSecondOrderDto = new CreateOrderDto(depotId, supplierId, Set.of(thirdPackage, fourthPackage), "Notes for second order");
        orderService.acceptOrder(createFirstOrderDto);
        orderService.acceptOrder(createSecondOrderDto);
        List<CoordinatesDto> route = orderService.startPlanningOrders(depotId);
        List<Order> startedOrders = orderRepository.findOrdersStartedInDepot(depotId);
        assertEquals(2, startedOrders.size());
        assertThat(route).containsExactlyInAnyOrderElementsOf(Set.of(firstPackage, secondPackage, thirdPackage, fourthPackage).stream().map(CreatePackageDto::coordinates).collect(Collectors.toSet()));
    }

}
