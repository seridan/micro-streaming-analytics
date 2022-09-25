package com.example.microstreaminganalytics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "statistics")
public class Statistics {
    @Id
    private String id;
    private LocalDateTime timestamp;
    private double mean;
    private double median;
    private double mode;
    private double standarDeviation;
    private double quarter1;
    private double quarter2;
    private double quarter3;
    private double maxValue;
    private double minValue;
}
