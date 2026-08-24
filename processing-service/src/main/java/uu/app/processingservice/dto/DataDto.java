package uu.app.processingservice.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Builder
@Value
@RequiredArgsConstructor
public class DataDto {
    Long id;
    String name;
    String description;
    Integer amount;
    Double value;
    Long timestamp;
    String payload;
}
