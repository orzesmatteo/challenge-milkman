package it.milkman.challenge.route.impl;

import it.milkman.challenge.dto.CoordinatesDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class RouteCalculatorTests {

    @Test
    void routeCalculate() {
        RouteCalculatorDummyLatitudeImpl routeCalculatorLatitude = new RouteCalculatorDummyLatitudeImpl();
        RouteCalculatorDummyLongitudeImpl routeCalculatorLongitude = new RouteCalculatorDummyLongitudeImpl();
        CoordinatesDto first = new CoordinatesDto(4.0, 1.0);
        CoordinatesDto second = new CoordinatesDto(2.0, 4.0);
        CoordinatesDto third = new CoordinatesDto(3.0, 3.0);
        CoordinatesDto fourth = new CoordinatesDto(1.0, 2.0);
        //Latitude
        List<CoordinatesDto> routeLatitude = routeCalculatorLatitude.calculateRoute(null, Set.of(first, second, third, fourth), null);
        List<CoordinatesDto> expectedLatitudeList = new ArrayList<>();
        expectedLatitudeList.add(fourth);
        expectedLatitudeList.add(second);
        expectedLatitudeList.add(third);
        expectedLatitudeList.add(first);
        assertThat(routeLatitude).containsExactlyElementsOf(expectedLatitudeList);
        //Longitude
        List<CoordinatesDto> routeLongitude = routeCalculatorLongitude.calculateRoute(null, Set.of(first, second, third, fourth), null);
        List<CoordinatesDto> expectedLongitudeList = new ArrayList<>();
        expectedLongitudeList.add(first);
        expectedLongitudeList.add(fourth);
        expectedLongitudeList.add(third);
        expectedLongitudeList.add(second);
        assertThat(routeLongitude).containsExactlyElementsOf(expectedLongitudeList);
    }

}
