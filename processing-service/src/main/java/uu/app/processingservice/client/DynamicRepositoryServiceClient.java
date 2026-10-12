package uu.app.processingservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import uu.app.loadbalancer.loadbalancer.RegistryLoadBalancer;
import uu.app.processingservice.dto.DataDto;
import uu.app.processingservice.properties.ConnectionProperties;
import uu.app.registryclient.dto.InstanceInfo;

@Component
@Profile("dynamic")
@RequiredArgsConstructor
public class DynamicRepositoryServiceClient implements RepositoryServiceClient {

    private final ConnectionProperties properties;
    private final RepositoryServiceClientHelper clientHelper;
    private final RegistryLoadBalancer loadBalancer;

    @Override
    public DataDto getData(Long id) {
        InstanceInfo instanceInfo = loadBalancer.getInstance(properties.getName());
        return clientHelper.getData(instanceInfo.getIpAddress(), instanceInfo.getPort(), id);
    }

    @Override
    public DataDto saveData(DataDto dto) {
        InstanceInfo instanceInfo = loadBalancer.getInstance(properties.getName());
        return clientHelper.saveData(instanceInfo.getIpAddress(), instanceInfo.getPort(), dto);
    }

    @Override
    public DataDto updateData(Long id, DataDto dto) {
        InstanceInfo instanceInfo = loadBalancer.getInstance(properties.getName());
        return clientHelper.updateData(instanceInfo.getIpAddress(), instanceInfo.getPort(), id, dto);
    }

    @Override
    public void deleteData(Long id) {
        InstanceInfo instanceInfo = loadBalancer.getInstance(properties.getName());
        clientHelper.deleteData(instanceInfo.getIpAddress(), instanceInfo.getPort(), id);
    }
}
