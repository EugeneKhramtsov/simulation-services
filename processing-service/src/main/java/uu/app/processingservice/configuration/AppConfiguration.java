package uu.app.processingservice.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;
import uu.app.processingservice.properties.ConnectionProperties;
import uu.app.processingservice.properties.ProcessingProperties;

@Configuration
@EnableConfigurationProperties({ConnectionProperties.class, ProcessingProperties.class})
public class AppConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RestClient restClient(RestClient.Builder restClientBuilder) {
        return restClientBuilder.build();
    }
}
