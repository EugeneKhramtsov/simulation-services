package uu.app.processingservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import uu.app.processingservice.dto.DataDto;
import uu.app.processingservice.properties.ConnectionProperties;

@Component
@RequiredArgsConstructor
public class RepositoryServiceClient {

    public static final String SCHEME = "http";
    public static final String SIMULATION_DATA = "/simulation/data";
    private final ConnectionProperties properties;
    private final RestClient restClient;

    public DataDto getData(Long id) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.scheme(SCHEME)
                        .host(properties.getHost())
                        .port(properties.getPort())
                        .path(SIMULATION_DATA)
                        .queryParam("id", id)
                        .build())
                .retrieve()
                .body(DataDto.class);
    }

    public DataDto saveData(DataDto dto) {
        return restClient.post()
                .uri(uriBuilder -> uriBuilder.scheme(SCHEME)
                        .host(properties.getHost())
                        .port(properties.getPort())
                        .path(SIMULATION_DATA)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .retrieve()
                .body(DataDto.class);
    }

    public DataDto updateData(Long id, DataDto dto) {
        return restClient.put()
                .uri(uriBuilder -> uriBuilder.scheme(SCHEME)
                        .host(properties.getHost())
                        .port(properties.getPort())
                        .path(SIMULATION_DATA)
                        .queryParam("id", id)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .retrieve()
                .body(DataDto.class);
    }

    public void deleteData(Long id) {
        restClient.delete()
                .uri(uriBuilder -> uriBuilder.scheme(SCHEME)
                        .host(properties.getHost())
                        .port(properties.getPort())
                        .path(SIMULATION_DATA)
                        .queryParam("id", id)
                        .build())
                .retrieve()
                .toBodilessEntity();
    }
}
