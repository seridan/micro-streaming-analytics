package com.example.microstreaminganalytics;

import com.example.microstreaminganalytics.entity.Datastream;
import com.example.microstreaminganalytics.service.MicroStreamingAnalyticsService;
import com.example.microstreaminganalytics.service.impl.MicroStreamingAnalyticsServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;

import static org.springframework.integration.dsl.Transformers.fromJson;

@Configuration
public class IntegrationConfig {
    public static final int PERIOD = 5000;
    @Value("${app.queue.name}")
    private String queue;
    Logger logger = LoggerFactory.getLogger(MicroStreamingAnalyticsService.class);

    private final MicroStreamingAnalyticsServiceImpl microStreamingAnalyticsService;


    public IntegrationConfig(MicroStreamingAnalyticsServiceImpl microStreamingAnalyticsService) {
        this.microStreamingAnalyticsService = microStreamingAnalyticsService;
    }

    @Bean
    public IntegrationFlow integrationFlow1(ConnectionFactory connectionFactory) {
        return IntegrationFlows.from(Amqp.inboundPolledAdapter(connectionFactory, queue),
                sourcePollingChannelAdapterSpec -> sourcePollingChannelAdapterSpec.poller(Pollers.fixedDelay(PERIOD)))
            .transform(fromJson(Datastream.class))
            .handle(message -> {
                logger.info("Message read from INTEGRATION FLOW : " + message);
                microStreamingAnalyticsService.persistStatistics((Datastream) message.getPayload());
            })
            .get();
    }
}
