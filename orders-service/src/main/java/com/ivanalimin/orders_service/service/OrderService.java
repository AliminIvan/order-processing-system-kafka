package com.ivanalimin.orders_service.service;

import com.ivanalimin.orders_service.model.Order;
import com.ivanalimin.orders_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String NEW_ORDERS_TOPIC = "new-orders";

    @Transactional
    public Order createOrder(Order order) {
        order.setStatus(Order.OrderStatus.NEW);
        Order savedOrder = repository.save(order);
        kafkaTemplate.send(NEW_ORDERS_TOPIC, savedOrder);
        return savedOrder;
    }

    @Transactional
    public Order updateOrderStatus(UUID orderId, Order.OrderStatus status) {
        Order order = repository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(status);
        return repository.save(order);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }
}
