package com.MicroServiceProjectWithKafka.Inventory.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "inventory")
public class Inventory {

    @Id
    private String inventoryId;
    private String productId;
    private BigDecimal price;
    private Integer quantity;
}
