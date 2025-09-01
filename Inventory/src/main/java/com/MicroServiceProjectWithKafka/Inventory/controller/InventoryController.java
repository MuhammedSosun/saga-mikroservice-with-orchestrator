package com.MicroServiceProjectWithKafka.Inventory.controller;

import com.MicroServiceProjectWithKafka.Inventory.dto.InventoryDto;
import org.springframework.http.ResponseEntity;

public interface InventoryController {

    ResponseEntity<InventoryDto> createInventory(InventoryDto inventoryDto);
}
