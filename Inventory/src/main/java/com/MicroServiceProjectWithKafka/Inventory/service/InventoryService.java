package com.MicroServiceProjectWithKafka.Inventory.service;

import com.MicroServiceProjectWithKafka.Inventory.dto.InventoryCheckEvent;
import com.MicroServiceProjectWithKafka.Inventory.dto.InventoryDto;

import java.util.List;

public interface InventoryService {

    public InventoryDto createInventory(InventoryDto inventoryDto);
    public void checkInventory(InventoryCheckEvent event);
    public List<InventoryDto> findAll();
}
