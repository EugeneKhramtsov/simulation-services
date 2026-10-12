package uu.app.registryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uu.app.registryservice.dto.InstanceInfo;
import uu.app.registryservice.dto.InstanceStatus;
import uu.app.registryservice.repository.RegistryRepository;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppsService {

    private final RegistryRepository repository;

    public void save(InstanceInfo newInfo) {
        String appName = newInfo.getAppName();
        newInfo.setStatus(InstanceStatus.UP);
        newInfo.setTimestamp(Instant.now().toEpochMilli());
        var appInfo = repository.get(appName);
        for (InstanceInfo info : appInfo) {
            if (info.getInstanceId().equals(newInfo.getInstanceId())) {
                updateStatus(appName, newInfo.getInstanceId(), InstanceStatus.UP);
                log.info("Instance already registered, updating, serviceInfo: {}", newInfo);
                return;
            }
        }
        appInfo.add(newInfo);
        repository.save(appName, appInfo);
        log.info("New instance registered, serviceInfo: {}", newInfo);
    }

    public void update(InstanceInfo info) {
        String appName = info.getAppName();
        String instanceId = info.getInstanceId();
        boolean isUpdated = updateStatus(appName, instanceId, InstanceStatus.UP);
        if (!isUpdated) {
            save(info);
        }
    }

    public List<InstanceInfo> get(String appName) {
        return repository.get(appName);
    }

    public Map<String, List<InstanceInfo>> getAll() {
        return repository.getAll();
    }

    public void cancel(String appName, String instanceId) {
        updateStatus(appName, instanceId, InstanceStatus.DOWN);
    }

    private boolean updateStatus(String appName, String instanceId, InstanceStatus status) {
        var appInfo = repository.get(appName);
        boolean updated = false;
        for (InstanceInfo info : appInfo) {
            if (info.getInstanceId().equals(instanceId)) {
                info.setStatus(status);
                info.setTimestamp(Instant.now().toEpochMilli());
                updated = true;
                log.info("Instance status updated, instanceId: {}", instanceId);
            }
        }
        if (!updated)
            log.warn("Failed to update status, appName = {}, instanceId = {}, status = {}", appName, instanceId, status);

        return updated;
    }
}
