package com.example.microstreaminganalytics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String timestamp;
    private String name;
    private String id;
    private String resultCode;
    private String resultDescription;
    private List<Variable> variableList;
    private List<Step> steps;
}
