package com.example.microstreaminganalytics.service.impl;

import com.example.microstreaminganalytics.entity.DeviceStatistics;
import com.example.microstreaminganalytics.entity.Statistics;
import com.example.microstreaminganalytics.entity.Variable;
import com.example.microstreaminganalytics.repository.MicroStreamingAnalyticsRepository;
import com.example.microstreaminganalytics.service.MicroStreamingAnalyticsService;
import com.example.microstreaminganalytics.utils.StatisticalCalculator;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MicroStreamingAnalyticsServiceImpl implements MicroStreamingAnalyticsService {
    private final StatisticalCalculator statisticalCalculator;
    private final MicroStreamingAnalyticsRepository microStreamingAnalyticsRepository;

    Logger logger = LoggerFactory.getLogger(MicroStreamingAnalyticsService.class);

    public MicroStreamingAnalyticsServiceImpl(StatisticalCalculator statisticalCalculator, MicroStreamingAnalyticsRepository microStreamingAnalyticsRepository) {
        this.statisticalCalculator = statisticalCalculator;
        this.microStreamingAnalyticsRepository = microStreamingAnalyticsRepository;
    }

    @Override
    public void persistStatistics(DeviceStatistics deviceStatistics) throws JsonProcessingException {
        Statistics statistics = obtainStatisticsFromMessage(deviceStatistics);

        logger.info("Operation from myQueue : " + microStreamingAnalyticsRepository.save(statistics));
    }

    private Statistics obtainStatisticsFromMessage(DeviceStatistics deviceStatistics) {
        Statistics statistics = statisticalCalculator.obtainStatisticCalculations(obtainVariablesValuesFromDeviceStatistics(deviceStatistics));
        statistics.setId(deviceStatistics.getOperation().getResponse().getId());

        return statistics;
    }

    private List<Integer> obtainVariablesValuesFromDeviceStatistics(DeviceStatistics deviceStatistics) {
        return deviceStatistics.getOperation().getResponse().getVariableList().stream()
            .map(Variable::getValue)
            .collect(Collectors.toList());
    }
}
