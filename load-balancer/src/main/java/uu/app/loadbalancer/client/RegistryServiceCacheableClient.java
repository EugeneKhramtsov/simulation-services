package uu.app.loadbalancer.client;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import uu.app.registryclient.client.RegistryServiceClient;
import uu.app.registryclient.dto.InstanceInfo;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RegistryServiceCacheableClient {

    public final RegistryServiceClient registryServiceClient;

    @Cacheable
    public List<InstanceInfo> getApp(String name) {
        return registryServiceClient.getApp(name);
    }
}
