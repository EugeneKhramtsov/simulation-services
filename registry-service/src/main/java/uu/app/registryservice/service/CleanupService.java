package uu.app.registryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uu.app.registryservice.dto.InstanceInfo;
import uu.app.registryservice.dto.InstanceStatus;
import uu.app.registryservice.repository.RegistryRepository;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class CleanupService {

    // TODO make properties
    public static final long CLEANUP_THRESHOLD = 60000L;
    public static final long AVAILABILITY_THRESHOLD = 30000L;
    private final RegistryRepository repository;

    @Scheduled(fixedDelayString = "${service.schedule.cleanup.delay-ms}", initialDelayString = "${service.schedule.cleanup.initial-delay-ms}")
    public void cleanup() {
        var registry = repository.getAll();
        long now = Instant.now().toEpochMilli();
        for (var it = registry.entrySet().iterator(); it.hasNext(); ) {
            var entry = it.next();
            var infoList = entry.getValue();
            boolean isDeleted = infoList.removeIf(info -> now - info.getTimestamp() > CLEANUP_THRESHOLD);
            if (entry.getValue().isEmpty()) {
                it.remove();
                log.info("Deleted app {} info {}", entry.getKey(),  infoList);
            }
        }

    }

    @Scheduled(fixedDelayString = "${service.schedule.availability.delay-ms}")
    public void checkAvailability() {
        var registry = repository.getAll();
        long now = Instant.now().toEpochMilli();
        for (var entry : registry.entrySet()) {
            var infoList = entry.getValue();
            for (InstanceInfo info : infoList) {
                if (!info.getStatus().isDown() && now - info.getTimestamp() > AVAILABILITY_THRESHOLD) {
                    info.setStatus(InstanceStatus.DOWN);
                    log.info("App {} is down {}", info.getAppName(), info);
                }
            }
        }
    }
}
