package uu.app.registryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InstanceInfo {
    private String instanceId;
    private String host;
    private String ipAddress;
    private int port;
    private String appName;
    private InstanceStatus status;

}
