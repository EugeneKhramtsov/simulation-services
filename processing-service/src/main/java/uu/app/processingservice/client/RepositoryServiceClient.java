package uu.app.processingservice.client;

import uu.app.processingservice.dto.DataDto;

public interface RepositoryServiceClient {

    DataDto getData(Long id);

    DataDto saveData(DataDto dto);

    DataDto updateData(Long id, DataDto dto);

    void deleteData(Long id);

}
