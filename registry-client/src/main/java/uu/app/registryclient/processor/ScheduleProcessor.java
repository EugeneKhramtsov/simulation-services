package uu.app.registryclient.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uu.app.registryclient.client.RegistryServiceClient;
import uu.app.registryclient.dto.InstanceInfo;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduleProcessor {

    private final RegistryServiceClient client;
    private final InstanceInfo info;

    @Scheduled(fixedRate = 30000L, initialDelay = 30000L)
    public void registryUpdate() {
        client.updateStatus(info);
        log.debug("Updated status on registry-service: {}", info);
    }
}
