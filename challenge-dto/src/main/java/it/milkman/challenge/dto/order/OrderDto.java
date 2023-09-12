package it.milkman.challenge.dto.order;

import it.milkman.challenge.dto.depot.DepotDto;
import it.milkman.challenge.dto.packages.PackageDto;
import it.milkman.challenge.dto.supplier.SupplierDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public record OrderDto(
        @NotNull
        UUID id,
        @NotNull
        Instant creation,
        @NotNull
        Instant lastUpdate,
        @NotNull
        SupplierDto supplier,
        @NotNull
        DepotDto depot,
        String notes,
        @NotEmpty
        Set<PackageDto> packages,
        @NotNull
        OrderStatusDto status,
        Instant planStart,
        Instant deliveryStart,
        Instant deliveryEnd
) implements Serializable {
}
