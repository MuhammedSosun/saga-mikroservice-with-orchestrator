package com.Orchestrator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StartPaymentDto {

    private Long orderId;

    private Long customerId;

    private BigDecimal amount;

    private List<ItemDto>  items;
}
