package uu.app.processingservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import uu.app.processingservice.dto.DataDto;

@Component
@RequiredArgsConstructor
public class RepositoryServiceClient {

    private static final String BASE_URL = "localhost:8181";
    private final RestClient restClient;

    public DataDto getData(Long id) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.scheme("http")
                        .host("localhost")
                        .port(8181)
                        .path("/simulation/data")
                        .queryParam("id", id)
                        .build())
                .retrieve()
                .body(DataDto.class);
    }

    public DataDto saveData(DataDto dto) {
        return restClient.post()
                .uri(uriBuilder -> uriBuilder.scheme("http")
                        .host("localhost")
                        .port(8181)
                        .path("/simulation/data")
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .retrieve()
                .body(DataDto.class);
    }

    public DataDto updateData(Long id, DataDto dto) {
        return restClient.put()
                .uri(uriBuilder -> uriBuilder.scheme("http")
                        .host("localhost")
                        .port(8181)
                        .path("/simulation/data")
                        .queryParam("id", id)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .retrieve()
                .body(DataDto.class);
    }

    public void deleteData(Long id) {
        restClient.delete()
                .uri(uriBuilder -> uriBuilder.scheme("http")
                        .host("localhost")
                        .port(8181)
                        .path("/simulation/data")
                        .queryParam("id", id)
                        .build())
                .retrieve()
                .toBodilessEntity();
    }
}
