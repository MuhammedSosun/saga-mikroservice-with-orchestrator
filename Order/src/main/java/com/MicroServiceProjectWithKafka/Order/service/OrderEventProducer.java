package com.MicroServiceProjectWithKafka.Order.service;

import com.MicroServiceProjectWithKafka.Order.dto.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void publishOrderCreatedEvent(OrderCreatedEvent event) {
        kafkaTemplate.send("order-events", event);
        log.info("OrderCreatedEvent sent to Orchestration Topic: {}", event);
    }
}
