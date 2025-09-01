package com.MicroServiceProjectWithKafka.Order.service.impl;

import com.MicroServiceProjectWithKafka.Order.dto.*;
import com.MicroServiceProjectWithKafka.Order.mapper.OrderMapper;
import com.MicroServiceProjectWithKafka.Order.model.Order;
import com.MicroServiceProjectWithKafka.Order.model.OrderStatus;
import com.MicroServiceProjectWithKafka.Order.repository.OrderRepository;
import com.MicroServiceProjectWithKafka.Order.service.OrderEventProducer;
import com.MicroServiceProjectWithKafka.Order.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;
    private final OrderMapper orderMapper;


    public OrderServiceImpl(OrderRepository orderRepository, OrderEventProducer orderEventProducer,  OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderEventProducer = orderEventProducer;
        this.orderMapper = orderMapper;
    }


    @Transactional
    @Override
    public OrderDto CreateOrder(OrderDto OrderDto) {
        Order order = orderMapper.toEntity(OrderDto);
        BigDecimal newPrice = BigDecimal.ZERO;
        List<ItemDto> itemDtos = new ArrayList<>();
        for (ItemDto item : order.getItems()) {
            itemDtos.add(item);
            BigDecimal itemPrice = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            newPrice =  newPrice.add(itemPrice);
        }
        order.setPrice(newPrice);
        order.setStatus(OrderStatus.CREATED);
        Order savedOrder = orderRepository.save(order);

        OrderCreatedEvent  orderCreatedEvent = new OrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getCustomerId(),
                savedOrder.getPrice(),
                savedOrder.getStatus(),
                itemDtos
        );

        orderEventProducer.publishOrderCreatedEvent(orderCreatedEvent);

        return orderMapper.toDto(savedOrder);
    }
    @Transactional
    @Override
    public OrderDto CancelOrder(OrderCancelledEvent event) {
        Order order = orderRepository.findById(event.getOrderId()).orElseThrow(()-> new RuntimeException("not found"));
        order.setStatus(OrderStatus.CANCELLED);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }
    @Transactional
    @Override
    public OrderDto completeOrder(OrderCompletedEvent orderCompletedEvent) {
        Order order = orderRepository.findById(orderCompletedEvent.getOrderId()).orElseThrow(()-> new RuntimeException("not found"));
        order.setStatus(OrderStatus.COMPLETED);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }


}
