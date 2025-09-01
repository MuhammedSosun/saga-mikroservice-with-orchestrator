package com.MicroServiceProjectWithKafka.Inventory.service;

import com.MicroServiceProjectWithKafka.Inventory.dto.InventoryCheckEvent;
import com.MicroServiceProjectWithKafka.Inventory.dto.InventoryDto;

public interface InventoryService {

    public InventoryDto createInventory(InventoryDto inventoryDto);
    public void checkInventory(InventoryCheckEvent event);
}
