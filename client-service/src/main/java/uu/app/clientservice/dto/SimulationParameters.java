package uu.app.clientservice.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class SimulationParameters {

    int parallelSimulations;
    int getRequestsPerMin;
    int postRequestsPerMin;
    int putRequestsPerMin;
    int deleteRequestsPerMin;

}
