package com.Orchestrator.consumer;

import com.Orchestrator.dto.CheckFailedInventory;
import com.Orchestrator.dto.OrderCancelledEvent;
import com.Orchestrator.service.OrchestratorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryFailedCheckConsumer {
    private final OrchestratorService orchestratorService;

    public InventoryFailedCheckConsumer(OrchestratorService orchestratorService) {
        this.orchestratorService = orchestratorService;
    }

    @KafkaListener(topics = "inventory-failed",groupId = "orchestrator-group")
    public void consume(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            CheckFailedInventory event = mapper.readValue(message,CheckFailedInventory.class);
            log.info("Received OrderCancelledEvent {}", event);
            orchestratorService.handleInventoryFailed(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
