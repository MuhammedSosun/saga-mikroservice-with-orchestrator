package com.MicroServiceProjectWithKafka.Inventory.repository;

import com.MicroServiceProjectWithKafka.Inventory.dto.InventoryDto;
import com.MicroServiceProjectWithKafka.Inventory.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory, String> {
    public Optional<Inventory> getInventoryByProductId(String productId);
}
