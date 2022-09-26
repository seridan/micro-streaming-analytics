package com.example.microstreaminganalytics.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface MicroStreamingAnalyticsService {
    void persistStatistics(String message) throws JsonProcessingException;
}
