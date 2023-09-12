package it.milkman.challenge.dto.order;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public record CreateOrderDto(
        @NotNull
        UUID depotId,
        @NotNull
        UUID supplierId,
        @NotNull
        Set<UUID> packagesIds,
        String notes
) implements Serializable {
}
