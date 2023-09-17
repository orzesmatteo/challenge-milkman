package it.milkman.challenge.server.service;

import it.milkman.challenge.dao.Depot;
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
import it.milkman.challenge.repository.PackageRepository;
import it.milkman.challenge.repository.SupplierRepository;
import it.milkman.challenge.route.api.RouteCalculator;
import it.milkman.challenge.server.exception.ResourceMismatchException;
import it.milkman.challenge.server.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
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

    private final PackageRepository packageRepository;

    @Transactional
    public UUID acceptOrder(CreateOrderDto createOrderDto) {
        Set<Package> packages = createOrderDto.packages().stream().map(createPackageDto ->
                new it.milkman.challenge.dao.Package(
                        addressMapper.dtoToDao(createPackageDto.address()),
                        coordinatesMapper.dtoToDao(createPackageDto.coordinates()),
                        createPackageDto.notesForDelivery(),
                        PackageStatus.WAITING,
                        null
                )
        ).collect(Collectors.toSet());
        packages = new HashSet<>(packageRepository.saveAll(packages));
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

    @Transactional
    public OrderDto editOrder(UUID orderId, EditOrderDto editOrderDto) {
        if (!editOrderDto.orderId().equals(orderId)) {
            throw new ResourceMismatchException("Mismatch between path and given orderId.", orderId, editOrderDto.orderId());
        }
        if (!depotRepository.existsById(editOrderDto.depotId())) {
            throw new ResourceNotFoundException("Depot not found for given Id.");
        }
        if (!supplierRepository.existsById(editOrderDto.supplierId())) {
            throw new ResourceNotFoundException("Supplier not found for given Id.");
        }
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found."));
        if (order.getStatus() != OrderStatus.WAITING) {
            throw new IllegalStateException("Cannot edit started orders."); //Caught by controller advice
        }
        order.setDepot(depotRepository.getReferenceById(editOrderDto.depotId()));
        order.setSupplier(supplierRepository.getReferenceById(editOrderDto.supplierId()));
        order.setNotes(editOrderDto.notes());
        return orderMapper.daoToDto(orderRepository.save(order));
    }

    public Page<OrderDto> searchOrders(OrderStatusDto orderStatus, UUID depotId, Pageable pageable) {
        return orderRepository.findByDepotAndStatus(depotId, OrderStatus.valueOf(orderStatus.name()), pageable).map(orderMapper::daoToDto);
    }

    @Transactional
    public List<CoordinatesDto> startPlanningOrders(UUID depotId) {
        logger.info("OrderService::startPlanningOrders");
        //STEP0 Retrieve Depot info
        Depot depot = depotRepository.findById(depotId).orElseThrow(() -> new ResourceNotFoundException("Depot not found for id: " + depotId));
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
        List<CoordinatesDto> route = routeCalculator.calculateRoute(
                coordinatesMapper.daoToDto(depot.getCoordinates()),
                waitingPackages.stream().map(aPackage -> coordinatesMapper.daoToDto(aPackage.getCoordinates())).collect(Collectors.toSet()),
                coordinatesMapper.daoToDto(depot.getCoordinates()));
        logger.info("OrderService::startPlanningOrders calculated route: %s".formatted(route.toString()));
        //STEP3 Return order for courier
        return route;
    }
}
