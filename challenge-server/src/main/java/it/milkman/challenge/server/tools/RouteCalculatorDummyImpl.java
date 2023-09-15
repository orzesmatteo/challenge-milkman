package it.milkman.challenge.server.tools;

import it.milkman.challenge.dto.CoordinatesDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class RouteCalculatorDummyImpl implements RouteCalculator {
    @Override
    public List<CoordinatesDto> calculateRoute(CoordinatesDto start, Set<CoordinatesDto> deliveries, CoordinatesDto end) {
        return null;//TODO
    }
}
