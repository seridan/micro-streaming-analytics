package com.example.microstreaminganalytics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Datastream {
    @Id
    private int id;
    private String version;
    private String device;
    private String path;
    private String trustedBoot;
    private List<Datastreams> datastreams;
}
