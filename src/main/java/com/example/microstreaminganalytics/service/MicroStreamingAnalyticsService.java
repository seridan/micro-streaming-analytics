package com.example.microstreaminganalytics.service;

import com.example.microstreaminganalytics.entity.DeviceStatistics;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MicroStreamingAnalyticsService {
    void persistStatistics(DeviceStatistics deviceStatistics) throws JsonProcessingException;
}
