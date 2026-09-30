package uu.app.registryclient.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import uu.app.registryclient.dto.InstanceInfo;
import uu.app.registryclient.properties.RegistryServiceProperties;

import java.net.URI;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistryServiceClient {

    public static final String SCHEME = "http";
    public static final String REGISTRY_APPS = "/registry/apps";
    private final RegistryServiceProperties properties;
    private final RestClient restClient;

    public void register(InstanceInfo info) {
        URI uri = URI.create(properties.getUrl());

        restClient.post()
                .uri(uriBuilder -> uriBuilder.scheme(SCHEME)
                        .host(uri.getHost())
                        .port(uri.getPort())
                        .path(REGISTRY_APPS)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(info)
                .retrieve()
                .toBodilessEntity();
    }

    public void updateStatus(InstanceInfo info) {
        URI uri = URI.create(properties.getUrl());

        restClient.put()
                .uri(uriBuilder -> uriBuilder.scheme(SCHEME)
                        .host(uri.getHost())
                        .port(uri.getPort())
                        .path(REGISTRY_APPS)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(info)
                .retrieve()
                .toBodilessEntity();
    }

    public void cancel(InstanceInfo info) {
        URI uri = URI.create(properties.getUrl());

        restClient.delete()
                .uri(uriBuilder -> uriBuilder.scheme(SCHEME)
                        .host(uri.getHost())
                        .port(uri.getPort())
                        .pathSegment(REGISTRY_APPS, info.getAppName(), info.getInstanceId())
                        .build())
                .retrieve()
                .toBodilessEntity();
    }
}
