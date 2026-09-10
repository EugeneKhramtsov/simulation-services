package uu.app.registryservice.repository;

import lombok.Value;
import org.springframework.stereotype.Repository;
import uu.app.registryservice.dto.InstanceInfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Value
@Repository
public class RegistryRepository {

    ConcurrentHashMap<String, List<InstanceInfo>> registry;

    public RegistryRepository() {
        this.registry = new ConcurrentHashMap<>();
    }

    public void save(String appName, List<InstanceInfo> info) {
        registry.put(appName, info);
    }

    public List<InstanceInfo> get(String appName) {
        return Optional.ofNullable(registry.get(appName)).orElse(new CopyOnWriteArrayList<>());
    }

    public Map<String, List<InstanceInfo>> getAll() {
        return registry;
    }

    public void deleteApp(String appName) {
        registry.remove(appName);
    }

    public void deleteInstance(String appName, String instanceId) {
        this.get(appName).removeIf(info -> info.getInstanceId().equals(instanceId));
    }
}
