package com.example.microstreaminganalytics.utils;

import com.example.microstreaminganalytics.entity.Statistics;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Component
public class StatisticalCalculator {

    private List<Integer> list;

    public Statistics obtainStatisticCalculations(List<Integer> datapointsValues) {
        list = datapointsValues;

        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
        list.forEach(descriptiveStatistics::addValue);

        Statistics statistics = new Statistics();
        statistics.setTimestamp(LocalDateTime.now());
        statistics.setMean(descriptiveStatistics.getMean());
        statistics.setMedian(descriptiveStatistics.getPercentile(50));
        statistics.setMode(calculateMode());
        statistics.setStandarDeviation(calculateStandarDeviation(descriptiveStatistics.getPopulationVariance()));
        statistics.setQuarter1(descriptiveStatistics.getPercentile(25));
        statistics.setQuarter2(descriptiveStatistics.getPercentile(50));
        statistics.setQuarter3(descriptiveStatistics.getPercentile(75));
        statistics.setMaxValue(descriptiveStatistics.getMax());
        statistics.setMinValue(descriptiveStatistics.getMin());

        return statistics;
    }

    private double calculateMode() {
        double mode = list.stream()
                .collect(Collectors.groupingBy(i -> i, TreeMap::new, Collectors.counting()))
                .entrySet().stream().min((a, b) -> {
                    if (!a.getValue().equals(b.getValue()))
                        return b.getValue().compareTo(a.getValue());
                    return a.getKey().compareTo(b.getKey());
                }).get().getKey();

        return mode;
    }

    private double calculateStandarDeviation(double variance) {
        return Math.sqrt(variance);
    }
}


