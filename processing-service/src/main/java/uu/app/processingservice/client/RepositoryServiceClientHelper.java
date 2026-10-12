package uu.app.processingservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import uu.app.processingservice.dto.DataDto;

@Component
@RequiredArgsConstructor
public class RepositoryServiceClientHelper {

    public static final String SCHEME = "http";
    public static final String SIMULATION_DATA = "/simulation/data";
    private final RestClient restClient;

    public DataDto getData(String host, int port, Long id) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.scheme(SCHEME)
                        .host(host)
                        .port(port)
                        .path(SIMULATION_DATA)
                        .queryParam("id", id)
                        .build())
                .retrieve()
                .body(DataDto.class);
    }

    public DataDto saveData(String host, int port, DataDto dto) {
        return restClient.post()
                .uri(uriBuilder -> uriBuilder.scheme(SCHEME)
                        .host(host)
                        .port(port)
                        .path(SIMULATION_DATA)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .retrieve()
                .body(DataDto.class);
    }

    public DataDto updateData(String host, int port, Long id, DataDto dto) {
        return restClient.put()
                .uri(uriBuilder -> uriBuilder.scheme(SCHEME)
                        .host(host)
                        .port(port)
                        .path(SIMULATION_DATA)
                        .queryParam("id", id)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .retrieve()
                .body(DataDto.class);
    }

    public void deleteData(String host, int port, Long id) {
        restClient.delete()
                .uri(uriBuilder -> uriBuilder.scheme(SCHEME)
                        .host(host)
                        .port(port)
                        .path(SIMULATION_DATA)
                        .queryParam("id", id)
                        .build())
                .retrieve()
                .toBodilessEntity();
    }
}
