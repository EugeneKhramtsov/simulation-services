package uu.app.processingservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uu.app.processingservice.client.RepositoryServiceClient;
import uu.app.processingservice.dto.DataDto;
import uu.app.processingservice.properties.SimulationProperties;

import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class DataService {

    private final Random random = new Random();
    private final RepositoryServiceClient client;
    private final SimulationProperties properties;

    public DataDto getData(Long id) {
        simulateWork();
        return client.getData(id);
    }

    public DataDto saveData(DataDto dto) {
        simulateWork();
        DataDto savedDto = client.saveData(dto);
        log.debug("Saved data with id: {}", savedDto.getId());

        return savedDto;
    }

    public DataDto updateData(Long id, DataDto dto) {
        simulateWork();
        return client.updateData(id, dto);
    }

    public void deleteData(Long id) {
        simulateWork();
        client.deleteData(id);
    }

    private void simulateWork() {
        int randomRoll = random.nextInt(100);
        if (randomRoll < properties.getPercentOfFailure()) {
            throw new RuntimeException("Predefined failure in processing-service occurred with randomRoll = " + randomRoll);
        }
        if (properties.getDelayMs() > 0) {
            try {
                Thread.sleep(properties.getDelayMs());
            } catch (InterruptedException e) {
                log.error("Failed to simulate work", e);
            }
        }
    }
}
