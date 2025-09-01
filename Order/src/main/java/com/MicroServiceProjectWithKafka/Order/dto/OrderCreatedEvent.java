package com.MicroServiceProjectWithKafka.Order.dto;

import com.MicroServiceProjectWithKafka.Order.model.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderCreatedEvent {
    private Long orderId;
    private Long customerId;
    private BigDecimal price;
    private OrderStatus status;
    private List<ItemDto> items;
}
