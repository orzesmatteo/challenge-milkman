package it.milkman.challenge.server.service;

import it.milkman.challenge.dao.Order;
import it.milkman.challenge.dao.Package;
import it.milkman.challenge.dao.enums.OrderStatus;
import it.milkman.challenge.dao.enums.PackageStatus;
import it.milkman.challenge.dto.depot.DepotDto;
import it.milkman.challenge.dto.order.CreateOrderDto;
import it.milkman.challenge.dto.order.EditOrderDto;
import it.milkman.challenge.dto.order.OrderDto;
import it.milkman.challenge.dto.order.OrderStatusDto;
import it.milkman.challenge.mapper.AddressMapper;
import it.milkman.challenge.mapper.CoordinatesMapper;
import it.milkman.challenge.mapper.OrderMapper;
import it.milkman.challenge.repository.DepotRepository;
import it.milkman.challenge.repository.OrderRepository;
import it.milkman.challenge.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final SupplierRepository supplierRepository;

    private final DepotRepository depotRepository;

    private final OrderMapper orderMapper;

    private final AddressMapper addressMapper;

    private final CoordinatesMapper coordinatesMapper;

    @Transactional
    public UUID acceptOrder(CreateOrderDto createOrderDto) {
        Set<Package> packages = createOrderDto.packages().stream().map(createPackageDto ->
                new it.milkman.challenge.dao.Package(
                        addressMapper.dtoToDao(createPackageDto.address()),
                        coordinatesMapper.dtoToDao(createPackageDto.coordinates()),
                        null,
                        createPackageDto.notesForDelivery(),
                        PackageStatus.WAITING,
                        null
                )
        ).collect(Collectors.toSet());
        Order order = new Order(
                supplierRepository.getReferenceById(createOrderDto.supplierId()),
                depotRepository.getReferenceById(createOrderDto.depotId()),
                createOrderDto.notes(),
                packages,
                OrderStatus.WAITING,
                null,
                null,
                null
        );
        return orderRepository.save(order).getId();
    }

    public OrderDto editOrder(UUID orderId, EditOrderDto orderDto) {
        return null;//TODO
    }

    public Page<OrderDto> searchOrders(OrderStatusDto orderStatus, UUID depotId, Pageable pageable) {
        return null;//TODO
    }

    public Set<DepotDto> startPlanningOrders(UUID depotId) {
        return null;//todo
    }
}
