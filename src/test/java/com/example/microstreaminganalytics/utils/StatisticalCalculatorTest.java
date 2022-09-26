package com.example.microstreaminganalytics.utils;

import com.example.microstreaminganalytics.entity.Statistics;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StatisticalCalculatorTest {

    @InjectMocks
    StatisticalCalculator statisticalCalculator;

    @Test
    void obtainStatisticCalculations() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(5);

        Statistics statistics = statisticalCalculator.obtainStatisticCalculations(list);

        assertNotNull(statistics);
        assertNotNull(statistics.getTimestamp());
        assertEquals(2.8, statistics.getMean());
        assertEquals(3.0, statistics.getMedian());
        assertEquals(3.0, statistics.getMode());
        assertEquals(1.32664991614216, statistics.getStandarDeviation());
        assertEquals(1.5, statistics.getQuarter1());
        assertEquals(3.0, statistics.getQuarter2());
        assertEquals(4.0, statistics.getQuarter3());
        assertEquals(5.0, statistics.getMaxValue());
        assertEquals(1.0, statistics.getMinValue());
    }
}