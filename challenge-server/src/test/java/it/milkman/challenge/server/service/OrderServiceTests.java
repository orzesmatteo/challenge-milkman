package it.milkman.challenge.server.service;

import it.milkman.challenge.TestHelper;
import it.milkman.challenge.dao.Depot;
import it.milkman.challenge.dao.Order;
import it.milkman.challenge.dao.Supplier;
import it.milkman.challenge.dto.order.CreateOrderDto;
import it.milkman.challenge.repository.DepotRepository;
import it.milkman.challenge.repository.OrderRepository;
import it.milkman.challenge.repository.PackageRepository;
import it.milkman.challenge.repository.SupplierRepository;
import it.milkman.challenge.server.ChallengeApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@SpringBootTest(classes = ChallengeApplication.class)
@ActiveProfiles("h2")
public class OrderServiceTests {

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
        UUID depotId = depotRepository.save(new Depot("warehouseName", TestHelper.getAddressForTests(), TestHelper.getCoordinatesForTests())).getId();
        UUID supplierId = supplierRepository.save(new Supplier("name")).getId();
        CreateOrderDto createOrderDto = new CreateOrderDto(depotId, supplierId, Set.of(), "Notes");
        UUID orderId = orderService.acceptOrder(createOrderDto);
        Optional<Order> order = orderRepository.findByIdFull(orderId);
        assert (order.isPresent());
        assert (order.get().getPackages().size() == createOrderDto.packages().size());
        assert (order.get().getDepot().getId().equals(createOrderDto.depotId()));
        assert (order.get().getSupplier().getId().equals(createOrderDto.supplierId()));
    }

}
