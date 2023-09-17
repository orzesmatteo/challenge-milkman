package it.milkman.challenge.server.config;

import it.milkman.challenge.route.api.RouteCalculator;
import it.milkman.challenge.route.impl.RouteCalculatorDummyLatitudeImpl;
import it.milkman.challenge.route.impl.RouteCalculatorDummyLongitudeImpl;
import it.milkman.challenge.route.property.RouteCalculatorImpl;
import it.milkman.challenge.route.property.RouteCalculatorProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
@EnableConfigurationProperties(RouteCalculatorProperties.class)
public class ApplicationConfiguration {

    @Bean
    public RouteCalculator routeCalculator(RouteCalculatorProperties routeCalculatorProperties) {
        if (Objects.requireNonNull((routeCalculatorProperties.getImpl())) == RouteCalculatorImpl.LATITUDE) {
            return new RouteCalculatorDummyLatitudeImpl();
        } else if ((routeCalculatorProperties.getImpl()) == RouteCalculatorImpl.LONGITUDE) {
            return new RouteCalculatorDummyLongitudeImpl();
        }
        throw new IllegalStateException("ApplicationConfiguration::routeCalculator() wrong value of property defining calculator impl");
    }
}
