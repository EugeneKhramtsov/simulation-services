package uu.app.registryclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class RegistryServiceClient {

    public static final String SCHEME = "http";
    public static final String REGISTRY_APPS = "/registry/apps";
    private final ConnectionProperties properties;
    private final RestClient restClient;

    public void register(InstanceInfo info) {
        try {
            restClient.post()
                    .uri(uriBuilder -> uriBuilder.scheme(SCHEME)
                            .host(properties.getHost())
                            .port(properties.getPort())
                            .path(REGISTRY_APPS)
                            .build())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(info)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            log.error("Exception on http call", e);
        }
    }
}
