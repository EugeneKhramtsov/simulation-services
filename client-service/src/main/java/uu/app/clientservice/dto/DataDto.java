package uu.app.clientservice.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Builder
@Value
@RequiredArgsConstructor
public class DataDto {

    public static final DataDto EMPTY_DATA = new DataDto(0L, "", "", 0, 0.0, 0L, "");

    Long id;
    String name;
    String description;
    Integer amount;
    Double value;
    Long timestamp;
    String payload;
}
