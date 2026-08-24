package uu.app.repositoryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class DataEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private int amount;
    private double value;
    private long timestamp;
    private String payload;
}
