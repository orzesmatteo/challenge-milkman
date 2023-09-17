package it.milkman.challenge.route.impl;

import it.milkman.challenge.dto.CoordinatesDto;
import it.milkman.challenge.route.api.RouteCalculator;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Dummy implementation of route calculator sorting by longitude
 */
public class RouteCalculatorDummyLongitudeImpl implements RouteCalculator {
    @Override
    public List<CoordinatesDto> calculateRoute(CoordinatesDto start, Set<CoordinatesDto> deliveries, CoordinatesDto end) {
        return deliveries.stream().sorted(Comparator.comparingDouble(CoordinatesDto::longitude)).collect(Collectors.toList());
    }
}
