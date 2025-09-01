package com.MicroServiceProjectWithKafka.Payment.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentFailedEvent {

    private Long orderId;
    private Long customerId;
    private BigDecimal price;
    private String errorMessage;
    private List<ItemDto> items;
}
