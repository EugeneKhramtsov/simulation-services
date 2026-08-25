package uu.app.clientservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uu.app.clientservice.client.ProcessingServiceClient;
import uu.app.clientservice.dto.DataDto;
import uu.app.clientservice.dto.SimulationParameters;
import uu.app.clientservice.generator.DataGenerator;
import uu.app.clientservice.properties.SimulationProperties;

import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class SimulationService {

    private final Random random = new Random();
    private final ProcessingServiceClient client;
    private final DataGenerator generator;
    private final SimulationProperties properties;

    @Async
    public void simulateClient(SimulationParameters parameters) {
        for (int i = 0; i < 100; i++) {
            DataDto dto = client.saveData(generator.createDataDto());
            log.debug("Saved data with id: {}", dto.getId());
        }
    }
}
