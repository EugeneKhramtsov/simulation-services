package uu.app.clientservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uu.app.clientservice.dto.SimulationParameters;

@Service
@RequiredArgsConstructor
public class SimulationService {

    private final ClientSimulator clientSimulator;

    public void startSimulation(SimulationParameters parameters) {
        clientSimulator.init();
        for (int i = 0; i < parameters.getParallelSimulations(); i++) {
            clientSimulator.startSavingData(parameters);
            clientSimulator.startReceivingData(parameters);
            clientSimulator.startUpdatingData(parameters);
            clientSimulator.startDeletingData(parameters);
        }
    }

    public void stopSimulation() {
        clientSimulator.stopSimulation();
    }
}
