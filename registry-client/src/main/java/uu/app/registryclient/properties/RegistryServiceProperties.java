package uu.app.registryclient.properties;

import lombok.Builder;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@Builder
@ConfigurationProperties(prefix = "registry-service")
public class RegistryServiceProperties {
    String url;
}
