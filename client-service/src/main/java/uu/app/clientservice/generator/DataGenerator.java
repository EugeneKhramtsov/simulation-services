package uu.app.clientservice.generator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uu.app.clientservice.dto.DataDto;

import java.time.Instant;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataGenerator {

    private static final List<String> adjectives = List.of("big", "little", "empty", "full", "strong", "weak");
    private static final List<String> nouns = List.of("soul", "devil", "angel", "seraph", "mug", "pepper");
    private final Random random = new Random();
    private final Instant instant = Instant.now();

    public DataDto createDataDto() {
        return DataDto.builder()
                .name(adjectives.get(random.nextInt(adjectives.size())) + "-" + nouns.get(random.nextInt(nouns.size())) + "-" + instant.toEpochMilli())
                .description(adjectives.get(random.nextInt(adjectives.size())) + "-" + nouns.get(random.nextInt(nouns.size())) + "-" + instant.toEpochMilli())
                .amount(random.nextInt(2000))
                .value(random.nextDouble(1.0))
                .timestamp(instant.toEpochMilli())
                .payload("payload")
                .build();
    }
}
