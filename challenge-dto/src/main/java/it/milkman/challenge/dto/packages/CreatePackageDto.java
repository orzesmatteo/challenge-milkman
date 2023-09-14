package it.milkman.challenge.dto.packages;

import it.milkman.challenge.dto.AddressDto;
import it.milkman.challenge.dto.CoordinatesDto;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record CreatePackageDto(
        @NotNull
        AddressDto address,
        @NotNull
        CoordinatesDto coordinates,
        String notesForDelivery
) implements Serializable {
}
