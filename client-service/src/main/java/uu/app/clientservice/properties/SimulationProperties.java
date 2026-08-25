package uu.app.clientservice.properties;

import lombok.Builder;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@Builder
@ConfigurationProperties(prefix = "service.simulation")
public class SimulationProperties {
    Long delayMs;
}
