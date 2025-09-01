package com.MicroServiceProjectWithKafka.Order.mapper;

import com.MicroServiceProjectWithKafka.Order.dto.OrderDto;
import com.MicroServiceProjectWithKafka.Order.model.Order;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface OrderMapper {

    public OrderDto toDto(Order order);
    public Order toEntity(OrderDto orderDto);

}
