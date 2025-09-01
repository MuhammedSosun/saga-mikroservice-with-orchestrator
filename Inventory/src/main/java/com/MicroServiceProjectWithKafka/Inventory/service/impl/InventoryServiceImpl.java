package com.MicroServiceProjectWithKafka.Inventory.service.impl;

import com.MicroServiceProjectWithKafka.Inventory.dto.InventoryCheckEvent;
import com.MicroServiceProjectWithKafka.Inventory.dto.InventoryDto;
import com.MicroServiceProjectWithKafka.Inventory.mapper.InventoryMapper;
import com.MicroServiceProjectWithKafka.Inventory.model.CheckCompletedInventory;
import com.MicroServiceProjectWithKafka.Inventory.model.CheckFailedInventory;
import com.MicroServiceProjectWithKafka.Inventory.model.Inventory;
import com.MicroServiceProjectWithKafka.Inventory.model.ItemDto;
import com.MicroServiceProjectWithKafka.Inventory.repository.InventoryRepository;
import com.MicroServiceProjectWithKafka.Inventory.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String INVENTORY_CHECKED_TOPIC = "inventory-checked";
    private static final String INVENTORY_CHECK_FAILED_TOPIC = "inventory-failed";
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
    public InventoryServiceImpl(KafkaTemplate<String, Object> kafkaTemplate, InventoryRepository inventoryRepository, InventoryMapper inventoryMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
    }
    public void checkInventory(InventoryCheckEvent event) {
        for (ItemDto item : event.getItems()) {
            Optional<Inventory> opt = inventoryRepository.getInventoryByProductId(item.getProductId());
            if (opt.isEmpty()){
                log.warn("Product not found: {}", item.getProductId());
                publishInventoryFailed(event,"Product not found: " + item.getProductId());
                continue;
            }
            Inventory inventory = opt.get();
            if (inventory.getQuantity() >= item.getQuantity()) {
                inventory.setQuantity(inventory.getQuantity() - item.getQuantity());
                inventoryRepository.save(inventory);

                publishInventoryChecked(event);
            }
            else {
                log.warn("Not enough stock for product {}", item.getProductId());
                publishInventoryFailed(event, "Not enough stock for product " + item.getProductId());
            }
        }
    }
    private void publishInventoryChecked(InventoryCheckEvent event) {
        CheckCompletedInventory payload = new CheckCompletedInventory(
                event.getOrderId(), event.getCustomerId(), UUID.randomUUID().toString(), event.getItems()
        );
        kafkaTemplate.send(INVENTORY_CHECKED_TOPIC, payload);
    }
    private void publishInventoryFailed(InventoryCheckEvent event, String reason) {
        CheckFailedInventory payload = new CheckFailedInventory(
                event.getOrderId(), event.getCustomerId(), reason, event.getItems()
        );
        kafkaTemplate.send(INVENTORY_CHECK_FAILED_TOPIC, payload);
    }


    public InventoryDto createInventory(InventoryDto inventoryDto) {
        Inventory inventory = inventoryMapper.toEntity(inventoryDto);

        log.info(" Inventory created {}", inventory.toString());
        return inventoryMapper.toDto(inventoryRepository.insert(inventory));
    }
}
