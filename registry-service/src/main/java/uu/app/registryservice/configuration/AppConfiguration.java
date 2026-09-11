package uu.app.registryservice.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import uu.app.registryservice.properties.ScheduleProperties;

@Configuration
@Import(ExecutorConfiguration.class)
@EnableConfigurationProperties(ScheduleProperties.class)
public class AppConfiguration {
}
