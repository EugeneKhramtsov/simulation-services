package uu.app.clientservice.client;

import uu.app.clientservice.dto.DataDto;

public interface ProcessingServiceClient {

    DataDto getData(Long id);

    DataDto saveData(DataDto dto);

    DataDto updateData(Long id, DataDto dto);

    void deleteData(Long id);

}
