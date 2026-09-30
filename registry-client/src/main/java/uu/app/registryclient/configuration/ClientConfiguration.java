package uu.app.registryclient.configuration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import uu.app.registryclient.dto.InstanceInfo;
import uu.app.registryclient.factory.InstanceInfoFactory;
import uu.app.registryclient.properties.RegistryServiceProperties;

@AutoConfiguration
@ConditionalOnBooleanProperty("registry-service.discovery.enabled")
@EnableConfigurationProperties(RegistryServiceProperties.class)
@ComponentScan(basePackages = "uu.app.registryclient")
@EnableAsync
@EnableScheduling
public class ClientConfiguration {

    @Bean
    public InstanceInfo instanceInfo(InstanceInfoFactory infoFactory) {
        return infoFactory.getInstanceInfo();
    }
}
