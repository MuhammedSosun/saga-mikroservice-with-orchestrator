package com.MicroServiceProjectWithKafka.Payment.service;

import com.MicroServiceProjectWithKafka.Payment.model.StartPaymentEvent;

public interface PaymentService {
    public void processPayment(StartPaymentEvent event);
}
