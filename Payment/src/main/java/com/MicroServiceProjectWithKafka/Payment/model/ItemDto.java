package com.MicroServiceProjectWithKafka.Payment.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {


    private String productId;
    public BigDecimal price;
    public Integer quantity;
}
