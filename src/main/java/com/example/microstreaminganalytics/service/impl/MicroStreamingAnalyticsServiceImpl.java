package com.example.microstreaminganalytics.service.impl;

import com.example.microstreaminganalytics.entity.Datapoint;
import com.example.microstreaminganalytics.entity.Datastream;
import com.example.microstreaminganalytics.entity.Datastreams;
import com.example.microstreaminganalytics.entity.Statistics;
import com.example.microstreaminganalytics.repository.MicroStreamingAnalyticsRepository;
import com.example.microstreaminganalytics.service.MicroStreamingAnalyticsService;
import com.example.microstreaminganalytics.utils.StatisticalCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void persistStatistics(Datastream datastream)  {
        List<Statistics> statistics = obtainStatisticsFromDataStream(datastream);

        logger.info("Operation from myQueue : " + microStreamingAnalyticsRepository.saveAll(statistics));
    }

    private List<Statistics> obtainStatisticsFromDataStream(Datastream datastream) {
        Iterable<Datastreams> datastreamsList = new ArrayList<>(datastream.getDatastreams());
        List<Statistics> statisticsList = new ArrayList<>();

        datastreamsList.forEach(datastreams -> {
            Statistics statistics = statisticalCalculator.obtainStatisticCalculations(obtainDatapointValuesFromDatastreams(datastreams));
            statistics.setId(datastreams.getId());
            statisticsList.add(statistics);
        });

        return statisticsList;
    }

    private List<Integer> obtainDatapointValuesFromDatastreams(Datastreams datastreams) {
        return datastreams.getDatapoints().stream()
                .map(Datapoint::getValue)
                .collect(Collectors.toList());
    }
}
