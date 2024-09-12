package com.ivanalimin.shipping_service.service;

import com.ivanalimin.dto.OrderDTO;
import com.ivanalimin.shipping_service.model.Order;
import com.ivanalimin.shipping_service.repository.OrderRepository;
import com.ivanalimin.shipping_service.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShippingService {

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;
    private final OrderRepository orderRepository;

    @KafkaListener(topics = "payed-orders", groupId = "shipping-service")
    public void listen(OrderDTO orderDTO) {
        log.info("Processing order for shipping: {}", orderDTO.getId());
        Order order = OrderMapper.toOrderEntity(orderDTO);
        order.setStatus(Order.OrderStatus.SHIPPED);
        shipOrder(order);
        orderRepository.save(order);
        kafkaTemplate.send("sent-orders", OrderMapper.toOrderDTO(order));
        log.info("Order shipped: {}", order.getId());
    }

    private void shipOrder(Order order) {
        log.info("Order {} shipped successfully", order.getId());
    }
}
