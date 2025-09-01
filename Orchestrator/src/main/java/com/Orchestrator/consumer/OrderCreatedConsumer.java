package com.Orchestrator.consumer;


import com.Orchestrator.dto.OrderCreatedEvent;
import com.Orchestrator.service.OrchestratorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderCreatedConsumer {

    private final OrchestratorService orchestratorService;

    public OrderCreatedConsumer(OrchestratorService orchestratorService) {
        this.orchestratorService = orchestratorService;
    }

    @KafkaListener(topics = "order-events",groupId = "orchestrator-group")
    public void consume(String message) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            OrderCreatedEvent event = mapper.readValue(message,OrderCreatedEvent.class);
            log.info("Received order created event: {}", event);
            orchestratorService.handleOrderCreated(event);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
