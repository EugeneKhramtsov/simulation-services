package uu.app.registryclient;

import lombok.Builder;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@Builder
@ConfigurationProperties(prefix = "service.connection.registry-service")
public class ConnectionProperties {
    String host;
    int port;
}
