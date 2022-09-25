package com.example.microstreaminganalytics.repository;

import com.example.microstreaminganalytics.entity.Statistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MicroStreamingAnalyticsRepository extends MongoRepository<Statistics, String> {
}
