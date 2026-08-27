package uu.app.clientservice.properties;

import lombok.Builder;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@Builder
@ConfigurationProperties(prefix = "service.connection.processing-service")
public class ConnectionProperties {
    String host;
    int port;
}
