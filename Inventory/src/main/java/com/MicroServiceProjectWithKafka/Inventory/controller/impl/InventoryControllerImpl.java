package com.MicroServiceProjectWithKafka.Inventory.controller.impl;

import com.MicroServiceProjectWithKafka.Inventory.controller.InventoryController;
import com.MicroServiceProjectWithKafka.Inventory.dto.InventoryDto;
import com.MicroServiceProjectWithKafka.Inventory.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/inventory")
public class InventoryControllerImpl implements InventoryController {
    private final InventoryService inventoryService;

    public InventoryControllerImpl(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto) {
        return ResponseEntity.ok(inventoryService.createInventory(inventoryDto));
    }

}
