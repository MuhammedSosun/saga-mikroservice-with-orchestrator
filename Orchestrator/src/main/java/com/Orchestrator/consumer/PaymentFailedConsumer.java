package com.Orchestrator.consumer;

import com.Orchestrator.dto.PaymentFailedEvent;
import com.Orchestrator.service.OrchestratorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentFailedConsumer {
    private final OrchestratorService orchestratorService;

    public PaymentFailedConsumer(OrchestratorService orchestratorService) {
        this.orchestratorService = orchestratorService;
    }

    @KafkaListener(topics = "payment-failed",groupId = "orchestrator-group")
    public void consume(String message) {
       try {
           ObjectMapper mapper = new ObjectMapper();
           PaymentFailedEvent event = mapper.readValue(message,PaymentFailedEvent.class);
           log.info("Consumer received PaymentFailedEvent: {}", event);
           orchestratorService.handlePaymentFailed(event);
       } catch (JsonProcessingException e) {
           throw new RuntimeException(e);
       }
    }
}
