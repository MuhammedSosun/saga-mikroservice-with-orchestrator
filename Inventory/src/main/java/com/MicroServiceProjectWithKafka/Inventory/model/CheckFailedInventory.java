package com.MicroServiceProjectWithKafka.Inventory.model;

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
    private String errorMessage;
    private List<ItemDto> items;

}
