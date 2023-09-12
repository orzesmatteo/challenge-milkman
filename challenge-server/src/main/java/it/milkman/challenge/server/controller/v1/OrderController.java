package it.milkman.challenge.server.controller.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.milkman.challenge.dto.order.CreateOrderDto;
import it.milkman.challenge.dto.order.EditOrderDto;
import it.milkman.challenge.dto.order.OrderDto;
import it.milkman.challenge.server.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Accept orders from external systems.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Order accepted."),
            @ApiResponse(responseCode = "400", description = "Body is not correct.")
    })
    public UUID acceptOrder(@RequestBody CreateOrderDto createOrderDto) {
        return orderService.acceptOrder(createOrderDto);
    }

    @PutMapping("/{orderId}")
    @Operation(summary = "Allows order modification from external systems.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order edit accepted."),
            @ApiResponse(responseCode = "400", description = "Body is not correct."),
            @ApiResponse(responseCode = "409", description = "Identifier does not match.")
    })
    public @Valid OrderDto editOrder(@PathVariable UUID orderId, @Valid @RequestBody EditOrderDto editOrderDto) {
        return orderService.editOrder(orderId, editOrderDto);
    }

    @GetMapping
    @Operation(summary = "Search orders with pagination based on optional filter params.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Search successful."),
            @ApiResponse(responseCode = "404", description = "Input depotId is not found.")
    })
    public Page<@Valid OrderDto> searchOrders(
            @RequestParam(required = false) String orderStatus,
            @RequestParam(required = false) UUID depotId,
            @PageableDefault(size = 20) @SortDefault(sort = "creation", direction = Sort.Direction.ASC) Pageable pageable) {
        return orderService.searchOrders(orderStatus, depotId, pageable);
    }

}
