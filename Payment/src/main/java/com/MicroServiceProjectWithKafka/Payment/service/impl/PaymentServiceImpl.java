package com.MicroServiceProjectWithKafka.Payment.service.impl;

import com.MicroServiceProjectWithKafka.Payment.model.*;
import com.MicroServiceProjectWithKafka.Payment.repository.PaymentRepository;
import com.MicroServiceProjectWithKafka.Payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {


    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final PaymentRepository paymentRepository;
    private static final String PAYMENT_PROCESSED_TOPIC = "payment-processed";
    private static final String PAYMENT_FAILED_TOPIC = "payment-failed";
    public PaymentServiceImpl(KafkaTemplate<String, Object> kafkaTemplate, PaymentRepository paymentRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.paymentRepository = paymentRepository;
    }

    public void processPayment(StartPaymentEvent event){
        try {
            log.info("Received processPayment method: {}", event);
            boolean isPaymentSuccessful = Math.random()<0.8;
            if (isPaymentSuccessful) {
                String transactionId = UUID.randomUUID().toString();
                PaymentProcessEvent paymentProcessEvent = new PaymentProcessEvent(
                        event.getOrderId(),
                        event.getCustomerId(),
                        event.getAmount(),
                        transactionId,
                        event.getItems()
                );
                Payment payment = new Payment();
                for (ItemDto item : event.getItems()) {
                    payment.setCustomerId(event.getCustomerId());
                    payment.setOrderId(event.getOrderId());
                    payment.setItem(item.getProductId());
                    paymentRepository.save(payment);
                }
                kafkaTemplate.send(PAYMENT_PROCESSED_TOPIC, paymentProcessEvent);
                log.info("PaymentProcessEvent sent succesfully for order: {}", paymentProcessEvent);
            }else {

                PaymentFailedEvent  paymentFailedEvent = new PaymentFailedEvent(
                        event.getOrderId(),
                        event.getCustomerId(),
                        event.getAmount(),
                        "Payment proccess failed",
                        event.getItems()
                );
                kafkaTemplate.send(PAYMENT_FAILED_TOPIC, paymentFailedEvent);
                log.info("Payment processing failed for order: {}", event);
            }

        }catch (Exception e){
            log.error("Exception occurred while processing payment: {}", e.getMessage());
            PaymentFailedEvent paymentFailedEvent = new PaymentFailedEvent(
                    event.getOrderId(),
                    event.getCustomerId(),
                    event.getAmount(),
                    "Payment process failed" + e.getMessage(),
                    event.getItems()
            );
            kafkaTemplate.send(PAYMENT_FAILED_TOPIC, paymentFailedEvent);
            throw e;
        }
    }

}
