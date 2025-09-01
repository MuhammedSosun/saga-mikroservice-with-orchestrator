package com.MicroServiceProjectWithKafka.Inventory.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {

    private String inventoryId;
    private String productId;
    private BigDecimal price;
    private Integer quantity;
}
