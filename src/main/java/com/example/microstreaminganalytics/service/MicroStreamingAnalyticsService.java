package com.example.microstreaminganalytics.service;

import com.example.microstreaminganalytics.entity.Datastream;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MicroStreamingAnalyticsService {
    void persistStatistics(Datastream datastream);
}
