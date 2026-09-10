package uu.app.registryservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ExecutorConfiguration.class)
public class AppConfiguration {
}
