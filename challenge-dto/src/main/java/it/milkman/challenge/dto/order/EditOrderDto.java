package it.milkman.challenge.dto.order;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

//The external system can modify only certain fields of the order.????
public record EditOrderDto(
        @NotNull
        UUID orderId,
        @NotNull
        UUID depotId,
        @NotNull
        UUID supplierId,
        String notes
) implements Serializable {
}
