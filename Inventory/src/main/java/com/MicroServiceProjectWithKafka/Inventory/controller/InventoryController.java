package com.MicroServiceProjectWithKafka.Inventory.controller;

import com.MicroServiceProjectWithKafka.Inventory.dto.InventoryDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InventoryController {

    ResponseEntity<InventoryDto> createInventory(InventoryDto inventoryDto);
    ResponseEntity<List<InventoryDto>> findAll();
    ResponseEntity<InventoryDto> getInventory(String productId);
}
