package it.milkman.challenge.dto.packages;

import it.milkman.challenge.dto.AddressDto;
import it.milkman.challenge.dto.CoordinatesDto;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public record PackageDto(
        @NotNull
        UUID id,
        @NotNull
        Instant creation,
        @NotNull
        Instant lastUpdate,
        @NotNull
        AddressDto address,
        @NotNull
        CoordinatesDto coordinates,
        String notesForDelivery,
        @NotNull
        PackageStatusDto status,
        Instant deliveryDate
) implements Serializable {
}
