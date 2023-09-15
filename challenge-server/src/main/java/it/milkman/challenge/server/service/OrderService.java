package it.milkman.challenge.server.service;

import it.milkman.challenge.dao.Order;
import it.milkman.challenge.dao.Package;
import it.milkman.challenge.dao.enums.OrderStatus;
import it.milkman.challenge.dao.enums.PackageStatus;
import it.milkman.challenge.dto.CoordinatesDto;
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
import it.milkman.challenge.server.tools.RouteCalculator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    private final SupplierRepository supplierRepository;

    private final DepotRepository depotRepository;

    private final OrderMapper orderMapper;

    private final AddressMapper addressMapper;

    private final CoordinatesMapper coordinatesMapper;

    private final RouteCalculator routeCalculator;

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

    @Transactional
    public List<CoordinatesDto> startPlanningOrders(UUID depotId) {
        logger.info("OrderService::startPlanningOrders");
        //STEP1 Set the Order as started.
        List<Order> waitingOrders = orderRepository.findOrdersWaitingInDepot(depotId);
        List<Package> waitingPackages = new ArrayList<>();
        waitingOrders.forEach(order -> waitingPackages.addAll(order.getPackages()));
        //Status Update of Order
        waitingOrders.forEach(order -> {
            order.setStatus(OrderStatus.STARTED);
            order.setPlanStart(Instant.now());
        });
        //STEP2 Calculate order of delivery with external bean
        //STEP3 Return order for courier
        return null;//todo
    }
}
