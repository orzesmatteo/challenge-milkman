package it.milkman.challenge.server.service;

import it.milkman.challenge.dto.order.OrderDto;
import it.milkman.challenge.mapper.OrderMapper;
import it.milkman.challenge.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public Set<OrderDto> getAll() {
        return orderRepository.findAll().stream().map(orderMapper::daoToDto).collect(Collectors.toSet());
    }

    public void acceptOrder(OrderDto orderDto) {

    }
}
