package com.MicroServiceProjectWithKafka.Order.service;

import com.MicroServiceProjectWithKafka.Order.dto.OrderCancelledEvent;
import com.MicroServiceProjectWithKafka.Order.dto.OrderCompletedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderConsumer {

    private final OrderService orderService;

    public OrderConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = "order-cancelled",groupId = "order-service-group")
    public void consumeOrderCancelled(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            OrderCancelledEvent event = mapper.readValue(message,OrderCancelledEvent.class);
            log.info("Received OrderCancelledEvent: {}", event);
            orderService.CancelOrder(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @KafkaListener(topics = "order-completed",groupId = "order-service-group")
    public void consumeOrderCompleted(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            OrderCompletedEvent event = mapper.readValue(message,OrderCompletedEvent.class);
            log.info("Received OrderCompletedEvent: {}", event);
            orderService.completeOrder(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
