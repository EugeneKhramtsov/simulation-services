package uu.app.clientservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import uu.app.clientservice.client.ProcessingServiceClient;
import uu.app.clientservice.dto.DataDto;
import uu.app.clientservice.dto.SimulationParameters;
import uu.app.clientservice.generator.DataGenerator;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Component
@AllArgsConstructor
public class ClientSimulator {

    private final Random random = new Random();
    private final ProcessingServiceClient client;
    private final DataGenerator generator;
    private final AtomicBoolean isRunning = new AtomicBoolean(true);
    private final List<Long> actualDataIds = new CopyOnWriteArrayList<>();

    public void init() {
        for (int i = 0; i < 100; i++) {
            DataDto dto = client.saveData(generator.createDataDto());
            actualDataIds.add(dto.getId());
            log.debug("Data saved, id: {}", dto.getId());
        }
    }

    @Async
    public void startSavingData(SimulationParameters parameters) {
        isRunning.set(true);
        while (isRunning.get()) {
            DataDto dto = client.saveData(generator.createDataDto());
            actualDataIds.add(dto.getId());
            log.debug("Data saved, id: {}", dto.getId());
            if (parameters.getPostRequestsPerMin() != 0) {
                wait(parameters.getPostRequestsPerMin());
            }
        }
    }

    @Async
    public void startReceivingData(SimulationParameters parameters) {
        isRunning.set(true);
        while (isRunning.get()) {
            if (actualDataIds.isEmpty())
                continue;
            DataDto dto = client.getData(actualDataIds.get(random.nextInt(actualDataIds.size())));
            log.debug("Data received, id: {}", dto.getId());
            if (parameters.getGetRequestsPerMin() != 0) {
                wait(parameters.getGetRequestsPerMin());
            }
        }
    }

    @Async
    public void startUpdatingData(SimulationParameters parameters) {
        isRunning.set(true);
        while (isRunning.get()) {
            if (actualDataIds.isEmpty())
                continue;
            DataDto dto = client.updateData(actualDataIds.get(random.nextInt(actualDataIds.size())), generator.createDataDto());
            log.debug("Data updated, id: {}", dto.getId());
            if (parameters.getPutRequestsPerMin() != 0) {
                wait(parameters.getPutRequestsPerMin());
            }
        }
    }

    @Async
    public void startDeletingData(SimulationParameters parameters) {
        isRunning.set(true);
        while (isRunning.get()) {
            if (actualDataIds.isEmpty())
                continue;
            int index = random.nextInt(actualDataIds.size());
            long id = actualDataIds.get(index);
            client.deleteData(id);
            actualDataIds.remove(index);
            log.debug("Data deleted, id: {}", id);
            if (parameters.getDeleteRequestsPerMin() != 0) {
                wait(parameters.getDeleteRequestsPerMin());
            }
        }
    }

    public void stopSimulation() {
        isRunning.set(false);
    }

    private static void wait(int requestsPerMin) {
        try {
            Thread.sleep(60000 / requestsPerMin);
        } catch (InterruptedException e) {
            log.error("Failed to wait", e);
        }
    }
}
