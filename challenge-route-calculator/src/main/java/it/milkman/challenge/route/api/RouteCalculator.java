package it.milkman.challenge.route.api;

import it.milkman.challenge.dto.CoordinatesDto;

import java.util.List;
import java.util.Set;

public interface RouteCalculator {

    List<CoordinatesDto> calculateRoute(CoordinatesDto start, Set<CoordinatesDto> deliveries, CoordinatesDto end);
}
