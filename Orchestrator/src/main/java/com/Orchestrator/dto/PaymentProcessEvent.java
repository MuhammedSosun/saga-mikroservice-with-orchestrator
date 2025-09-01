package com.Orchestrator.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentProcessEvent {
    private Long orderId;
    private Long customerId;
    private BigDecimal price;
    private String transactionId;
    private List<ItemDto> items;
}
