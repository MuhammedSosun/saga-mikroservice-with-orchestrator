package com.MicroServiceProjectWithKafka.Inventory.service;

import com.MicroServiceProjectWithKafka.Inventory.dto.InventoryCheckEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor // constructor injection için
public class InventoryCheckConsumer {

    private static final String INVENTORY_CHECK_TOPIC = "inventory-check";

    private final InventoryService inventoryService; // injection ✅

    @KafkaListener(topics = INVENTORY_CHECK_TOPIC, groupId = "inventory-service-group")
    public void handleInventoryEvent(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InventoryCheckEvent event = mapper.readValue(message, InventoryCheckEvent.class);
            log.info("Received InventoryCheckEvent: {}", event);

            inventoryService.checkInventory(event); // iş mantığı
        } catch (JsonProcessingException e) {
            log.error("Failed to parse InventoryCheckEvent: {}", message, e);
            // burada exception fırlatma -> retry/rebalance tetiklenmez
        } catch (Exception e) {
            log.error("Unexpected error in InventoryCheckConsumer", e);
            // burada da swallow etmek isteyebilirsin veya errorHandler'a bırakabilirsin
        }
    }
}
