package com.MicroServiceProjectWithKafka.Inventory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StartInventoryChcekEvent {
    private Long orderId;
    private String productId;
    private BigDecimal price;
    private Integer quantity;
}
