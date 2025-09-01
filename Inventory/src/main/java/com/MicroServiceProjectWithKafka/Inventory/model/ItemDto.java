package com.MicroServiceProjectWithKafka.Inventory.model;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDto {

    private String productId;
    public BigDecimal price;
    public Integer quantity;

}
