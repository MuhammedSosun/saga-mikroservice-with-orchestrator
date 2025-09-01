package com.Orchestrator.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CheckFailedInventory {

    private Long orderId;
    private Long customerId;
    private List<ItemDto> items;

}
