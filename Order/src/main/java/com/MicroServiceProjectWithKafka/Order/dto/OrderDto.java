package com.MicroServiceProjectWithKafka.Order.dto;

import com.MicroServiceProjectWithKafka.Order.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private Long customerId;

    private List<ItemDto> items;
}
