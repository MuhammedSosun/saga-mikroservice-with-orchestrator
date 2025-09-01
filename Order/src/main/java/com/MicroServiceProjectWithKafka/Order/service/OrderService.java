package com.MicroServiceProjectWithKafka.Order.service;

import com.MicroServiceProjectWithKafka.Order.dto.OrderCancelledEvent;
import com.MicroServiceProjectWithKafka.Order.dto.OrderCompletedEvent;
import com.MicroServiceProjectWithKafka.Order.dto.OrderDto;
import com.MicroServiceProjectWithKafka.Order.model.Order;

public interface OrderService {

    OrderDto CreateOrder(OrderDto OrderDto);
    OrderDto CancelOrder(OrderCancelledEvent event);
    OrderDto completeOrder(OrderCompletedEvent orderCompletedEvent);
}
