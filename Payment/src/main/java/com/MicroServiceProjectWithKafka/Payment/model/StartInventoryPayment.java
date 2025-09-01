package com.MicroServiceProjectWithKafka.Payment.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StartInventoryPayment {
    private String productId;
    private String orderId;
    private Integer quantity;
    private BigDecimal price;

}
