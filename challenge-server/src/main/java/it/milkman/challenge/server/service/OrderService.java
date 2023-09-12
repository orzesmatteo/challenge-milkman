package it.milkman.challenge.server.service;

import it.milkman.challenge.dto.depot.DepotDto;
import it.milkman.challenge.dto.order.CreateOrderDto;
import it.milkman.challenge.dto.order.EditOrderDto;
import it.milkman.challenge.dto.order.OrderDto;
import it.milkman.challenge.dto.order.OrderStatusDto;
import it.milkman.challenge.mapper.OrderMapper;
import it.milkman.challenge.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    public UUID acceptOrder(CreateOrderDto orderDto) {
        return null; //TODO
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
