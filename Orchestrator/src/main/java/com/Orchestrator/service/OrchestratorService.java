package com.Orchestrator.service;

import com.Orchestrator.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrchestratorService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String PAYMENT_START_TOPIC = "payment-start";
    private static final String PAYMENT_FAILED_TOPIC = "payment-failed";
    private static final String PAYMENT_REVERSE_TOPIC = "payment-reverse";
    private static final String ORDER_CANCELLED_TOPIC = "order-cancelled";
    private static final String ORDER_COMPLETED_TOPIC = "order-completed";
    private static final String INVENTORY_CHECK_TOPIC = "inventory-check";
    private static final String INVENTORY_REVERSE_TOPIC = "inventory-reverse";

    public OrchestratorService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info("Received OrderCreatedEvent {}", event);

        StartPaymentDto startPaymentDto = new StartPaymentDto(
                event.getOrderId(),
                event.getCustomerId(),
                event.getPrice(),
                event.getItems()
        );
        log.info("Received startPaymentDto {}", startPaymentDto);
        kafkaTemplate.send(PAYMENT_START_TOPIC, startPaymentDto);
        log.info("OrderCreatedEvent sent for order {}", startPaymentDto.getOrderId());

    }
    public void handelPaymentProcessed(PaymentProcessEvent event){
        log.info(" PaymentProcessEvent FOR ORDER {}", event);
        StartInventoryCheckEvent  startInventoryCheckEvent = new StartInventoryCheckEvent(
                event.getOrderId(),
                event.getCustomerId(),
                event.getItems()
        );
        kafkaTemplate.send(INVENTORY_CHECK_TOPIC, startInventoryCheckEvent);
        log.info("Inventory check event sent for order: {}", event);
    }


    public void handlePaymentFailed(PaymentFailedEvent event){
        log.info(" PaymentFailedEvent FOR ORDER {}", event);
        kafkaTemplate.send(ORDER_CANCELLED_TOPIC, event.getOrderId());
    }
    public void handleInventoryCheck(CheckCompletedInventory event){

        log.info(" InventoryCheckEvent FOR ORDER: {}",event);
        kafkaTemplate.send(ORDER_COMPLETED_TOPIC,event.getOrderId());
    }
    public void handleInventoryFailed(CheckFailedInventory event){
        log.info(" InventoryFailedEvent FOR ORDER {}", event);
        kafkaTemplate.send(ORDER_COMPLETED_TOPIC, event.getOrderId());
    }



}
