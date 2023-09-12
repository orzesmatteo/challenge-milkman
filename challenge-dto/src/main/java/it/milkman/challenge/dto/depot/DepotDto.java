package it.milkman.challenge.dto.depot;

import it.milkman.challenge.common.Constants;
import it.milkman.challenge.dto.AddressDto;
import it.milkman.challenge.dto.CoordinatesDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public record DepotDto(
        @NotNull
        UUID id,
        @NotNull
        Instant creation,
        @NotNull
        Instant lastUpdate,
        @Size(max = Constants.StringSizingConstants.MEDIUM)
        String warehouseName,
        @NotNull
        AddressDto addressDto,
        @NotNull
        CoordinatesDto coordinatesDto
) implements Serializable {
}
