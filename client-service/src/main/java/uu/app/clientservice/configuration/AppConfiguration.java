package uu.app.clientservice.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tools.jackson.databind.ObjectMapper;
import uu.app.clientservice.properties.ConnectionProperties;

@Configuration
@Import(ExecutorConfiguration.class)
@EnableAutoConfiguration
@EnableConfigurationProperties(ConnectionProperties.class)
public class AppConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
