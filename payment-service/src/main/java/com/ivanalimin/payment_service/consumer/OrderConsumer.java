package com.ivanalimin.payment_service.consumer;

import com.ivanalimin.dto.OrderDTO;

import com.ivanalimin.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final PaymentService paymentService;

    @KafkaListener(topics = "new-orders", groupId = "payment-service")
    public void listen(OrderDTO orderDTO) {
        paymentService.processPayment(orderDTO);
    }
}
