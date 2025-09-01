package com.Orchestrator.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CheckCompletedInventory {

    private Long orderId;
    private Long customerId;
    private String checkId;
    private List<ItemDto> items;
}
