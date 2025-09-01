package com.MicroServiceProjectWithKafka.Payment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StartPaymentEvent {


    private Long orderId;

    private Long customerId;

    private BigDecimal amount;

    private List<ItemDto> items;


}
