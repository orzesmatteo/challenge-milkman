package it.milkman.challenge.route.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "it.milkman.route.calculator")
public class RouteCalculatorProperties {

    private RouteCalculatorImpl impl;

    public RouteCalculatorProperties() {
    }

    public RouteCalculatorProperties(RouteCalculatorImpl impl) {
        this.impl = impl;
    }

    public RouteCalculatorImpl getImpl() {
        return impl;
    }

    public void setImpl(RouteCalculatorImpl impl) {
        this.impl = impl;
    }
}
