package com.Orchestrator.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderCreatedEvent {
    private Long orderId;
    private Long customerId;
    private BigDecimal price;
    private OrderStatus status;
    private List<ItemDto> items;
}
