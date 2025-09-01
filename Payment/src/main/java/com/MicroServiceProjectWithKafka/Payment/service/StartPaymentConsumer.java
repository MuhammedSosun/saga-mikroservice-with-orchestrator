package com.MicroServiceProjectWithKafka.Payment.service;

import com.MicroServiceProjectWithKafka.Payment.model.StartPaymentEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StartPaymentConsumer {
    private final PaymentService paymentService;
    private static final String PAYMENT_START_TOPIC = "payment-start";

    public StartPaymentConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(topics = PAYMENT_START_TOPIC,groupId = "payment-service-group")
    public void handelPaymentStartedEvent(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            StartPaymentEvent event = mapper.readValue(message,StartPaymentEvent.class);
            log.info("Received StartPaymentEvent: {}", event);
            paymentService.processPayment(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
