package it.milkman.challenge.dto.supplier;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public record SupplierDto(@NotNull
                          UUID id,
                          @NotNull
                          Instant creation,
                          @NotNull
                          Instant lastUpdate,
                          @NotEmpty
                          String name
) implements Serializable {
}
