package com.example.microstreaminganalytics.service.impl;

import com.example.microstreaminganalytics.entity.Datastream;
import com.example.microstreaminganalytics.entity.Datastreams;
import com.example.microstreaminganalytics.entity.Statistics;
import com.example.microstreaminganalytics.repository.MicroStreamingAnalyticsRepository;
import com.example.microstreaminganalytics.utils.StatisticalCalculator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MicroStreamingAnalyticsServiceImplTest {

    @Mock
    StatisticalCalculator statisticalCalculator;

    @Mock
    MicroStreamingAnalyticsRepository microStreamingAnalyticsRepository;

    @InjectMocks
    MicroStreamingAnalyticsServiceImpl microStreamingAnalyticsService;

    @Test
    void shouldPersistStatisticsFromMessage() {
        Datastream datastream = new Datastream();
        datastream.setDatastreams(List.of(mock(Datastreams.class)));

        when(statisticalCalculator.obtainStatisticCalculations(anyList())).thenReturn(mock(Statistics.class));

        microStreamingAnalyticsService.persistStatistics(datastream);

        verify(microStreamingAnalyticsRepository).saveAll(any());
        verify(statisticalCalculator).obtainStatisticCalculations(anyList());
    }
}
