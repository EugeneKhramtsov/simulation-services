package uu.app.registryclient.processor;

import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import uu.app.registryclient.client.RegistryServiceClient;
import uu.app.registryclient.dto.InstanceInfo;

@Slf4j
@Async
@Component
@RequiredArgsConstructor
class EventProcessor {

    private final RegistryServiceClient client;
    private final InstanceInfo info;

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        client.register(info);
        log.info("Service registered on registry-service: {}", info);
    }

    @PreDestroy
    public void destroy() {
        client.cancel(info);
        log.info("Service canceled on registry-service: {}", info);
    }
}
