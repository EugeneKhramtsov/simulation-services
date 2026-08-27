package uu.app.clientservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import uu.app.clientservice.dto.DataDto;
import uu.app.clientservice.properties.ConnectionProperties;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessingServiceClient {

    public static final String SCHEME = "http";
    public static final String SIMULATION_DATA = "/simulation/data";
    private final ConnectionProperties properties;
    private final RestClient restClient;

    public DataDto getData(Long id) {
        try {
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder.scheme(SCHEME)
                            .host(properties.getHost())
                            .port(properties.getPort())
                            .path(SIMULATION_DATA)
                            .queryParam("id", id)
                            .build())
                    .retrieve()
                    .body(DataDto.class);
        } catch (Exception e) {
            log.error("Exception on http call", e);
            return DataDto.EMPTY_DATA;
        }
    }

    public DataDto saveData(DataDto dto) {
        try {
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
        } catch (Exception e) {
            log.error("Exception on http call", e);
            return DataDto.EMPTY_DATA;
        }
    }

    public DataDto updateData(Long id, DataDto dto) {
        try {
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
        } catch (Exception e) {
            log.error("Exception on http call", e);
            return DataDto.EMPTY_DATA;
        }
    }

    public void deleteData(Long id) {
        try {
            restClient.delete()
                    .uri(uriBuilder -> uriBuilder.scheme(SCHEME)
                            .host(properties.getHost())
                            .port(properties.getPort())
                            .path(SIMULATION_DATA)
                            .queryParam("id", id)
                            .build())
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            log.error("Exception on http call", e);
        }
    }
}
