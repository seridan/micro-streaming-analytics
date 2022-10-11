package com.example.microstreaminganalytics.service;

import com.example.microstreaminganalytics.entity.Datastream;

public interface MicroStreamingAnalyticsService {
    void persistStatistics(Datastream datastream);
}
