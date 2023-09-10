package it.milkman.challenge.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public record OrderDto(
        UUID id,
        Instant creation,
        Instant lastUpdate,
        String notes
) implements Serializable {
}
