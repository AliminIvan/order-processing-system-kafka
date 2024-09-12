package com.ivanalimin.payment_service.service;

import com.ivanalimin.payment_service.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String PAYED_ORDERS_TOPIC = "payed-orders";

    public void processPayment(Order order) {
        log.info("Processing payment for order {}", order.getId());
        order.setStatus(Order.OrderStatus.PAYED);

        kafkaTemplate.send(PAYED_ORDERS_TOPIC, order);
        log.info("Payment processed for order {}", order.getId());
    }
}
