package uu.app.repositoryservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uu.app.repositoryservice.dto.DataDto;
import uu.app.repositoryservice.mapper.DataMapper;
import uu.app.repositoryservice.repository.DataRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DataService {

    private final DataRepository repository;
    private final DataMapper mapper;

    public Optional<DataDto> getData(Long id) {
        return repository.findById(id)
                .map(mapper::map);
    }

    public DataDto saveData(DataDto dto) {
        return mapper.map(repository.save(mapper.map(dto)));
    }

    public DataDto updateData(Long id, DataDto dto) {
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
        repository.deleteById(id);
    }
}
