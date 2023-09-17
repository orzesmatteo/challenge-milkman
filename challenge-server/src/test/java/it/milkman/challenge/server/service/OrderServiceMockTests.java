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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

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
    @InjectMocks
    private OrderService orderService;

    @Test
    void acceptOrder() {

        // GIVEN
        UUID depotId = UUID.randomUUID();
        Depot firstDepot = TestHelper.getFirstDepotForTests();
        firstDepot.setId(depotId);
        given(depotRep.getReferenceById(depotId)).willReturn(firstDepot);

        UUID supplierId = UUID.randomUUID();
        Supplier firstSupplier = new Supplier("Supplier1");
        firstSupplier.setId(supplierId);
        given(supplierRep.getReferenceById(supplierId)).willReturn(firstSupplier);

        UUID packageId = UUID.randomUUID();
        Package packageForOrder = new Package(TestHelper.getAddressForTests(), TestHelper.getCoordinatesForTests(), "Notes for delivery", PackageStatus.WAITING, null);
        packageForOrder.setId(packageId);
        given(packageRep.saveAll(any())).willReturn(new ArrayList<>(Set.of(packageForOrder)));

        UUID orderId = UUID.randomUUID();
        Order order = new Order(firstSupplier, firstDepot, "notes", Set.of(packageForOrder), OrderStatus.WAITING, null, null, null);
        order.setId(orderId);
        given(orderRep.save(any(Order.class))).willReturn(order);

        // WHEN
        CreatePackageDto createPackageDto = new CreatePackageDto(TestHelper.getAddressDtoForTests(), TestHelper.getCoordinatesDtoForTests(), "Notes for delivery");
        CreateOrderDto createOrderDto = new CreateOrderDto(depotId, supplierId, Set.of(createPackageDto), "notes");
        UUID createdOrderId = orderService.acceptOrder(createOrderDto);

        // THEN
        assertThat(createdOrderId).isEqualTo(orderId);
        //Improve with other assertions
    }

}
