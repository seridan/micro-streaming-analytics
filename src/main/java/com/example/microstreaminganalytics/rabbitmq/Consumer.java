package com.example.microstreaminganalytics.rabbitmq;

import com.example.microstreaminganalytics.service.impl.MicroStreamingAnalyticsServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private final MicroStreamingAnalyticsServiceImpl microStreamingAnalyticsService;

    public Consumer(MicroStreamingAnalyticsServiceImpl microStreamingAnalyticsService) {
        this.microStreamingAnalyticsService = microStreamingAnalyticsService;
    }

    @RabbitListener(queues = {"${app.queue.name}"})
    public void receive(@Payload String message) throws JsonProcessingException {
        microStreamingAnalyticsService.persistStatistics(message);
    }
}
