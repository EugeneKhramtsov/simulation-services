package uu.app.clientservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import uu.app.clientservice.dto.DataDto;
import uu.app.clientservice.properties.ConnectionProperties;

@Component
@Profile("static")
@RequiredArgsConstructor
public class StaticProcessingServiceClient implements ProcessingServiceClient {

    private final ConnectionProperties properties;
    private final RepositoryServiceClientHelper clientHelper;

    @Override
    public DataDto getData(Long id) {
        return clientHelper.getData(properties.getHost(), properties.getPort(), id);
    }

    @Override
    public DataDto saveData(DataDto dto) {
        return clientHelper.saveData(properties.getHost(), properties.getPort(), dto);
    }

    @Override
    public DataDto updateData(Long id, DataDto dto) {
        return clientHelper.updateData(properties.getHost(), properties.getPort(), id, dto);
    }

    @Override
    public void deleteData(Long id) {
        clientHelper.deleteData(properties.getHost(), properties.getPort(), id);
    }
}
