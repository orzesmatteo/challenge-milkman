package it.milkman.challenge.server.controller;

import it.milkman.challenge.dto.order.OrderDto;
import it.milkman.challenge.server.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Set<OrderDto> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    public void acceptOrder(OrderDto orderDto) {
        orderService.acceptOrder(orderDto);
    }

    @PutMapping
    public void editOrder(OrderDto orderDto) {
        orderService.acceptOrder(orderDto);
    }

}
