package com.example.microstreaminganalytics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Step {
    private String name;
    private String result;
    private String timestamp;
    private String description;
}
