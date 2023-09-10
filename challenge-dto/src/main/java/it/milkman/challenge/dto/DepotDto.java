package it.milkman.challenge.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public record DepotDto(
        UUID id,
        Instant creation,
        Instant lastUpdate,
        String warehouseName,
        double latitude,
        double longitude
) implements Serializable {
}
