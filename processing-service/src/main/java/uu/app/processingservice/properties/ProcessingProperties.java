package uu.app.processingservice.properties;

import lombok.Builder;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@Builder
@ConfigurationProperties(prefix = "service.processing")
public class ProcessingProperties {
    Long delayMs;
    float percentOfFailure;
}
