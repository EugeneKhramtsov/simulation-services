package uu.app.registryservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import uu.app.registryservice.dto.InstanceInfo;
import uu.app.registryservice.service.AppsService;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/registry/apps")
@AllArgsConstructor
public class AppsController {

    private final AppsService service;

    @PostMapping
    public void save(@RequestBody InstanceInfo info) {
        log.info("Registering instance, serviceInfo: {}", info);
        service.save(info);
    }

    @PutMapping
    public void update(@RequestBody InstanceInfo info) {
        log.info("Updating instance, serviceInfo: {}", info);
        service.update(info);
    }

    @GetMapping("{appName}")
    public List<InstanceInfo> get(@PathVariable String appName) {
        return service.get(appName);
    }

    @GetMapping
    public Map<String, List<InstanceInfo>> getAll() {
        return service.getAll();
    }

    @DeleteMapping("{appName}/{instanceId}")
    public void cancel(@PathVariable String appName, @PathVariable String instanceId) {
        log.info("Canceling instance, appName = {}, instanceId = {}", appName, instanceId);
        service.cancel(appName, instanceId);
    }
}
