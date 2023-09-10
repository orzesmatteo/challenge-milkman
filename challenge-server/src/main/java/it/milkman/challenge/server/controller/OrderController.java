package it.milkman.challenge.server.controller;

import it.milkman.challenge.dto.OrderDto;
import it.milkman.challenge.server.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
