package it.milkman.challenge.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record CoordinatesDto(
        @NotEmpty
        double latitude,
        @NotEmpty
        double longitude
) implements Serializable {
}