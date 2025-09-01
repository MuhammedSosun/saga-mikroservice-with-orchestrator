package com.Orchestrator.consumer;

import com.Orchestrator.dto.PaymentProcessEvent;
import com.Orchestrator.service.OrchestratorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentProcessedConsumer {

    private final OrchestratorService orchestratorService;

    public PaymentProcessedConsumer(OrchestratorService orchestratorService) {
        this.orchestratorService = orchestratorService;
    }

    @KafkaListener(topics = "payment-processed",groupId = "orchestrator-group")
    public void consume (String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            PaymentProcessEvent event = mapper.readValue(message,PaymentProcessEvent.class);
            log.info("Received PaymentProcessedConsumer {}", event);
            orchestratorService.handelPaymentProcessed(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
