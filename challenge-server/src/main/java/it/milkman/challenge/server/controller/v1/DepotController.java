package it.milkman.challenge.server.controller.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.milkman.challenge.dto.CoordinatesDto;
import it.milkman.challenge.server.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/depots")
@RequiredArgsConstructor
@Validated
public class DepotController {

    private final OrderService orderService;

    @PostMapping("/{depotId}/_startOrdersPlan")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Initiate planning computation from the specified depot to all the orders placed up to that point.")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Planning accepted."),
            @ApiResponse(responseCode = "404", description = "Input depotId is not found.")
    })
    public List<CoordinatesDto> planStart(@PathVariable UUID depotId) {
        return orderService.startPlanningOrders(depotId);
    }

}
