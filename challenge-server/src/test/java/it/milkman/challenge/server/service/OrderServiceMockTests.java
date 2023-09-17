package it.milkman.challenge.server.service;

import it.milkman.challenge.TestHelper;
import it.milkman.challenge.dao.Depot;
import it.milkman.challenge.dao.Order;
import it.milkman.challenge.dao.Package;
import it.milkman.challenge.dao.Supplier;
import it.milkman.challenge.dao.enums.OrderStatus;
import it.milkman.challenge.dao.enums.PackageStatus;
import it.milkman.challenge.dto.order.CreateOrderDto;
import it.milkman.challenge.dto.packages.CreatePackageDto;
import it.milkman.challenge.mapper.AddressMapper;
import it.milkman.challenge.mapper.CoordinatesMapper;
import it.milkman.challenge.mapper.OrderMapper;
import it.milkman.challenge.mapper.PackageMapper;
import it.milkman.challenge.repository.DepotRepository;
import it.milkman.challenge.repository.OrderRepository;
import it.milkman.challenge.repository.PackageRepository;
import it.milkman.challenge.repository.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class OrderServiceMockTests {

    @Mock
    private OrderRepository orderRep;
    @Mock
    private DepotRepository depotRep;
    @Mock
    private SupplierRepository supplierRep;
    @Mock
    private PackageRepository packageRep;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private PackageMapper packageMapper;
    @Mock
    private AddressMapper addressMapper;
    @Mock
    private CoordinatesMapper coordinatesMapper;
    @Captor
    ArgumentCaptor<Order> orderCaptor;
    @InjectMocks
    private OrderService orderService;

    @Test
    void acceptOrder() {

        // GIVEN
        UUID depotId = UUID.randomUUID();
        Instant depotCreation = Instant.now();
        Depot firstDepot = TestHelper.getFirstDepotForTests();
        firstDepot.setId(depotId);
        firstDepot.setCreation(depotCreation);
        firstDepot.setLastUpdate(depotCreation);
        given(depotRep.getReferenceById(depotId)).willReturn(firstDepot);

        UUID supplierId = UUID.randomUUID();
        Instant supplierCreation = Instant.now();
        Supplier firstSupplier = new Supplier("Supplier1");
        firstSupplier.setId(supplierId);
        firstSupplier.setCreation(supplierCreation);
        firstSupplier.setLastUpdate(supplierCreation);
        given(supplierRep.getReferenceById(supplierId)).willReturn(firstSupplier);

        UUID packageId = UUID.randomUUID();
        Instant packageCreation = Instant.now();
        Package packageForOrder = new Package(TestHelper.getAddressForTests(), TestHelper.getCoordinatesForTests(), "Notes for delivery", PackageStatus.WAITING, null);
        packageForOrder.setId(packageId);
        packageForOrder.setCreation(packageCreation);
        packageForOrder.setLastUpdate(packageCreation);
        given(packageRep.saveAll(any())).willReturn(new ArrayList<>(Set.of(packageForOrder)));

        UUID orderId = UUID.randomUUID();
        Instant orderCreation = Instant.now();
        Order order = new Order(firstSupplier, firstDepot, "notes", Set.of(packageForOrder), OrderStatus.WAITING, null, null, null);
        order.setId(orderId);
        order.setCreation(orderCreation);
        order.setLastUpdate(orderCreation);
        given(orderRep.save(any(Order.class))).willReturn(order);

        // WHEN
        CreatePackageDto createPackageDto = new CreatePackageDto(TestHelper.getAddressDtoForTests(), TestHelper.getCoordinatesDtoForTests(), "Notes for delivery");
        CreateOrderDto createOrderDto = new CreateOrderDto(depotId, supplierId, Set.of(createPackageDto), "notes");
        UUID createdOrderId = orderService.acceptOrder(createOrderDto);

        // THEN
        then(orderRep).should().save(orderCaptor.capture());
        Order orderSaved = orderCaptor.getValue();
        assertThat(createdOrderId).isEqualTo(orderId);
        assertThat(orderSaved.getDepot()).isEqualTo(order.getDepot());
        assertThat(orderSaved.getSupplier()).isEqualTo(order.getSupplier());
        assertThat(orderSaved.getPackages()).isEqualTo(order.getPackages());
        //Improve with other assertions
    }

}
