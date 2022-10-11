package com.example.microstreaminganalytics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Datapoint {
    private int from;
    private long at;
    private int value;
}
