package uu.app.repositoryservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uu.app.repositoryservice.dto.DataDto;
import uu.app.repositoryservice.mapper.DataMapper;
import uu.app.repositoryservice.properties.SimulationProperties;
import uu.app.repositoryservice.repository.DataRepository;

import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class DataService {

    private final Random random = new Random();
    private final SimulationProperties properties;
    private final DataRepository repository;
    private final DataMapper mapper;

    public Optional<DataDto> getData(Long id) {
        simulateWork();
        return repository.findById(id)
                .map(mapper::map);
    }

    public DataDto saveData(DataDto dto) {
        simulateWork();
        DataDto savedDto = mapper.map(repository.save(mapper.map(dto)));
        log.debug("Saved data: {}", savedDto);

        return savedDto;
    }

    public DataDto updateData(Long id, DataDto dto) {
        simulateWork();
        return repository.findById(id)
                .map(e -> {
                    e.setName(dto.getName());
                    e.setDescription(dto.getDescription());
                    e.setAmount(dto.getAmount());
                    e.setValue(dto.getValue());
                    e.setTimestamp(dto.getTimestamp());
                    e.setPayload(dto.getPayload());
                    return e;
                })
                .map(repository::save)
                .map(mapper::map)
                .orElse(null);
    }

    public void deleteData(Long id) {
        simulateWork();
        repository.deleteById(id);
    }

    private void simulateWork() {
        int randomRoll = random.nextInt(100);
        if (randomRoll < properties.getPercentOfFailure()) {
            throw new RuntimeException("Predefined failure in repository-service occurred with randomRoll = " + randomRoll);
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
