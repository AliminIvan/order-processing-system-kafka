package com.ivanalimin.payment_service.service;

import com.ivanalimin.dto.OrderDTO;
import com.ivanalimin.payment_service.model.Order;
import com.ivanalimin.payment_service.repository.OrderRepository;
import com.ivanalimin.payment_service.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;
    private static final String PAYED_ORDERS_TOPIC = "payed-orders";

    @Transactional
    public void processPayment(OrderDTO orderDTO) {
        log.info("Processing payment for order {}", orderDTO.getId());
        Order order = OrderMapper.toOrderEntity(orderDTO);
        order.setStatus(Order.OrderStatus.PAYED);
        orderRepository.save(order);

        kafkaTemplate.send(PAYED_ORDERS_TOPIC, OrderMapper.toOrderDTO(order));
        log.info("Payment processed for order {}", orderDTO.getId());
    }
}
