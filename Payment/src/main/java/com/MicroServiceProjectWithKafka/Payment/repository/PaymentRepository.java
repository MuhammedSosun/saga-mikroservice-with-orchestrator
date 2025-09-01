package com.MicroServiceProjectWithKafka.Payment.repository;

import com.MicroServiceProjectWithKafka.Payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
