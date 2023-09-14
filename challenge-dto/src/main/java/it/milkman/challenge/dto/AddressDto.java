package it.milkman.challenge.dto;

import it.milkman.challenge.common.Constants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record AddressDto(
        @NotEmpty
        @Size(max = Constants.StringSizingConstants.LARGE)
        String street,
        @NotEmpty
        @Size(max = Constants.StringSizingConstants.SHORT)
        String civic,
        @NotEmpty
        @Size(max = Constants.StringSizingConstants.SHORT)
        String cap,
        @NotEmpty
        @Size(max = Constants.StringSizingConstants.LARGE)
        String city,
        @NotEmpty
        @Size(max = Constants.StringSizingConstants.LARGE)
        String province
) implements Serializable {
}