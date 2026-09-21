package uu.app.clientservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uu.app.clientservice.dto.SimulationParameters;
import uu.app.clientservice.service.SimulationService;

@Slf4j
@RestController
@RequestMapping("/simulation")
@AllArgsConstructor
public class SimulationController {

    private final SimulationService service;

    @PostMapping("start")
    public void start(@RequestBody SimulationParameters parameters) {
        service.startSimulation(parameters);
    }

    @PostMapping("stop")
    public void stop() {
        service.stopSimulation();
    }
}
