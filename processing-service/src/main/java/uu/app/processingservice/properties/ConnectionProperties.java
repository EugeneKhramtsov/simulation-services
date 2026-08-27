package uu.app.processingservice.properties;

import lombok.Builder;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@Builder
@ConfigurationProperties(prefix = "service.connection.repository-service")
public class ConnectionProperties {
    String host;
    int port;
}
