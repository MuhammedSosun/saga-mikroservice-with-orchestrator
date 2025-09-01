package com.MicroServiceProjectWithKafka.Order.controller.impl;

import com.MicroServiceProjectWithKafka.Order.controller.OrderController;
import com.MicroServiceProjectWithKafka.Order.dto.OrderDto;
import com.MicroServiceProjectWithKafka.Order.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/order")
public class OrderControllerImpl implements OrderController {
    private final OrderService orderService;

    public OrderControllerImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    @Override
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return orderService.CreateOrder(orderDto);
    }
}
