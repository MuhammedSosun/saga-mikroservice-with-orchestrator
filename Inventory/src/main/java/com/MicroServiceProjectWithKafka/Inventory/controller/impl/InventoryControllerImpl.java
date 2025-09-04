package com.MicroServiceProjectWithKafka.Inventory.controller.impl;

import com.MicroServiceProjectWithKafka.Inventory.controller.InventoryController;
import com.MicroServiceProjectWithKafka.Inventory.dto.InventoryDto;
import com.MicroServiceProjectWithKafka.Inventory.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/list")
    @Override
    public ResponseEntity<List<InventoryDto>> findAll() {
        return ResponseEntity.ok(inventoryService.findAll());
    }




}
