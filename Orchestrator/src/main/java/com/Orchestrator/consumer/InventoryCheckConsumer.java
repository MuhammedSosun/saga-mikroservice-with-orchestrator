package com.Orchestrator.consumer;

import com.Orchestrator.dto.CheckCompletedInventory;
import com.Orchestrator.service.OrchestratorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryCheckConsumer {

    private final OrchestratorService orchestratorService;

    public InventoryCheckConsumer(OrchestratorService orchestratorService) {
        this.orchestratorService = orchestratorService;
    }
    @KafkaListener(topics = "inventory-checked",groupId = "orchestrator-group")
    public void consume(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            CheckCompletedInventory event = mapper.readValue(message,CheckCompletedInventory.class);
            log.info(" InventoryCheckEvent FOR ORDER {}",event);
            orchestratorService.handleInventoryCheck(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
