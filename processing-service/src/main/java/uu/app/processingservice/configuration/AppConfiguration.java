package uu.app.processingservice.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;
import uu.app.processingservice.properties.ConnectionProperties;
import uu.app.processingservice.properties.SimulationProperties;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties({ConnectionProperties.class, SimulationProperties.class})
public class AppConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
