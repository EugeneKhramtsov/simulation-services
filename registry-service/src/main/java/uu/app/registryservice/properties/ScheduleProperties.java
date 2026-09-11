package uu.app.registryservice.properties;

import lombok.Builder;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@Builder
@ConfigurationProperties(prefix = "service.cleanup")
public class ScheduleProperties {
    long deleteThresholdMs;
    long cancelThresholdMs;
}
