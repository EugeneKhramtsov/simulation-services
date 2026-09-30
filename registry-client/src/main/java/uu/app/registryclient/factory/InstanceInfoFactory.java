package uu.app.registryclient.factory;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import uu.app.registryclient.dto.InstanceInfo;
import uu.app.registryclient.dto.InstanceStatus;
import uu.app.registryclient.properties.RegistryServiceProperties;

import java.io.IOException;
import java.net.URI;

@Slf4j
@Component
@RequiredArgsConstructor
public class InstanceInfoFactory {

    @Getter
    private InstanceInfo instanceInfo;

    private final RegistryServiceProperties registryServiceProperties;
    private final Environment environment;

    @PostConstruct
    public void init() {
        log.info("Init Instance Info");
        URI registryServiceUrl = URI.create(registryServiceProperties.getUrl());

        String configuredIp = environment.getProperty("service.ip-address");

        String instanceAddress = null;
        try {
            instanceAddress = InstanceAddressResolver.resolve(configuredIp, registryServiceUrl.getHost(), registryServiceUrl.getPort());
        } catch (IOException e) {
            log.error("Failed to resolve Instance address via registry-service destination", e);
            return;
        }

        int port = environment.getProperty("server.port", int.class);
        String appName = environment.getProperty("spring.application.name");
        String instanceId = appName + ":" + instanceAddress + ":" + port;

        instanceInfo = new InstanceInfo(instanceId, "host", instanceAddress, port, appName, InstanceStatus.UP);
    }
}
