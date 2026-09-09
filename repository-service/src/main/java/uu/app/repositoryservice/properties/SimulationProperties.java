package uu.app.repositoryservice.properties;

import lombok.Builder;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@Builder
@ConfigurationProperties(prefix = "simulation")
public class SimulationProperties {
    Long delayMs;
    float percentOfFailure;
}
