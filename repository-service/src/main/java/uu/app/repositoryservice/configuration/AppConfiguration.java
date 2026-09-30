package uu.app.repositoryservice.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;
import uu.app.repositoryservice.properties.SimulationProperties;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties(SimulationProperties.class)
public class AppConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
