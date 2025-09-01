package com.MicroServiceProjectWithKafka.Inventory.mapper;

import com.MicroServiceProjectWithKafka.Inventory.dto.InventoryDto;
import com.MicroServiceProjectWithKafka.Inventory.model.Inventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    InventoryDto toDto(Inventory inventory);
    Inventory toEntity(InventoryDto inventoryDto);
}
