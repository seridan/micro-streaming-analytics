package com.example.microstreaminganalytics.service.impl;

import com.example.microstreaminganalytics.entity.Statistics;
import com.example.microstreaminganalytics.repository.MicroStreamingAnalyticsRepository;
import com.example.microstreaminganalytics.utils.StatisticalCalculator;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MicroStreamingAnalyticsServiceImplTest {

    @Mock
    StatisticalCalculator statisticalCalculator;

    @Mock
    MicroStreamingAnalyticsRepository microStreamingAnalyticsRepository;

    @InjectMocks
    MicroStreamingAnalyticsServiceImpl microStreamingAnalyticsService;

    @Test
    void shouldPersistStatisticsFromMessage() throws JsonProcessingException {
        String jsonMessage = "{\"version\":\"7.0\",\"operation\":{\"response\":{\"timestamp\":1432454278000,\"name\":\"REBOOT_EQUIPMENT\",\"id\":\"072b08d1-0fcb-4a0c-a2d8-99773f9b9327\",\"resultCode\":\"SUCCESSFUL\",\"resultDescription\":\"NoError.\",\"variableList\":[{\"name\":\"variable1\",\"value\":1},{\"name\":\"variable2\",\"value\":2},{\"name\":\"variable3\",\"value\":3},{\"name\":\"variable4\",\"value\":4},{\"name\":\"variable5\",\"value\":5}],\"steps\":[]}}}";

        when(statisticalCalculator.obtainStatisticCalculations(anyList())).thenReturn(mock(Statistics.class));
        when(microStreamingAnalyticsRepository.save(any())).thenReturn(any());

        microStreamingAnalyticsService.persistStatistics(jsonMessage);

        verify(microStreamingAnalyticsRepository).save(any());
        verify(statisticalCalculator).obtainStatisticCalculations(anyList());
    }
}