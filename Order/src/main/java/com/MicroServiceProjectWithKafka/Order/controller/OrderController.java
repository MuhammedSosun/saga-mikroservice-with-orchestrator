package com.MicroServiceProjectWithKafka.Order.controller;


import com.MicroServiceProjectWithKafka.Order.dto.OrderDto;

public interface OrderController {

    OrderDto createOrder(OrderDto orderDto);
}
