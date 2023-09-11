package it.milkman.challenge.dto.order;

import it.milkman.challenge.dto.packages.PackageDto;
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
        String notes,
        @NotEmpty
        Set<PackageDto> packages
) implements Serializable {
}
