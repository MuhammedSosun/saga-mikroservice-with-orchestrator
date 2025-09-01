package com.MicroServiceProjectWithKafka.Order.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@ToString
public class ItemDto {
    @Column(name = "product_id")
    private String productId;
    @Column(name = "price")
    public BigDecimal price;
    @Column(name = "quantity")
    public Integer quantity;

}
