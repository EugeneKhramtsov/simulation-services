package uu.app.processingservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uu.app.processingservice.client.RepositoryServiceClient;
import uu.app.processingservice.dto.DataDto;

@Service
@AllArgsConstructor
public class DataService {

    private final RepositoryServiceClient client;

    public DataDto getData(Long id) {
        return client.getData(id);
    }

    public DataDto saveData(DataDto dto) {
        return client.saveData(dto);
    }

    public DataDto updateData(Long id, DataDto dto) {
        return client.updateData(id, dto);
    }

    public void deleteData(Long id) {
        client.deleteData(id);
    }
}
