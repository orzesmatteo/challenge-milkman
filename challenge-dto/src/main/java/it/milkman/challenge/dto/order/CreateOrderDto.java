package it.milkman.challenge.dto.order;

import it.milkman.challenge.dto.packages.CreatePackageDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public record CreateOrderDto(
        @NotNull
        UUID depotId,
        @NotNull
        UUID supplierId,
        @NotEmpty
        Set<CreatePackageDto> packages,
        String notes
) implements Serializable {
}
