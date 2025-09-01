package com.MicroServiceProjectWithKafka.Inventory.dto;

import com.MicroServiceProjectWithKafka.Inventory.model.ItemDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InventoryCheckEvent {


    private Long orderId;
    private Long customerId;
    private List<ItemDto> items;

}
