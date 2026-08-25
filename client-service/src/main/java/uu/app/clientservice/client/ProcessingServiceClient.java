package uu.app.clientservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import uu.app.clientservice.dto.DataDto;
import uu.app.clientservice.properties.ConnectionProperties;

@Component
@RequiredArgsConstructor
public class ProcessingServiceClient {

    public static final String SCHEME = "http";
    public static final String SIMULATION_DATA = "/simulation/data";
    private final ConnectionProperties properties;
    private final RestClient restClient;

    public void getData(Long id) {
        restClient.get()
                .uri(uriBuilder -> uriBuilder.scheme(SCHEME)
                        .host(properties.getHost())
                        .port(properties.getPort())
                        .path(SIMULATION_DATA)
                        .queryParam("id", id)
                        .build())
                .retrieve()
                .toBodilessEntity();
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

    public void updateData(Long id, DataDto dto) {
        restClient.put()
                .uri(uriBuilder -> uriBuilder.scheme(SCHEME)
                        .host(properties.getHost())
                        .port(properties.getPort())
                        .path(SIMULATION_DATA)
                        .queryParam("id", id)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .retrieve()
                .toBodilessEntity();
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
