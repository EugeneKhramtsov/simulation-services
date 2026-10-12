package uu.app.loadbalancer.loadbalancer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uu.app.loadbalancer.client.RegistryServiceCacheableClient;
import uu.app.registryclient.dto.InstanceInfo;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class RegistryLoadBalancer {

    private final RegistryServiceCacheableClient client;
    private final AtomicInteger roundRobinIndex = new AtomicInteger(0);

    public InstanceInfo getInstance(String name) {
        List<InstanceInfo> instances = client.getApp(name);
        if (instances.size() <= roundRobinIndex.get()) {
            roundRobinIndex.set(0);
        }

        return instances.get(roundRobinIndex.getAndIncrement());
    }
}
