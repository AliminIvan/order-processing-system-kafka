package com.ivanalimin.notifications_service.service;

import com.ivanalimin.dto.OrderDTO;
import com.ivanalimin.notifications_service.model.Order;
import com.ivanalimin.notifications_service.repository.OrderRepository;
import com.ivanalimin.notifications_service.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final OrderRepository orderRepository;

    @KafkaListener(topics = "sent-orders", groupId = "notification-service")
    public void listen(OrderDTO orderDTO) {
        Order order = OrderMapper.toOrderEntity(orderDTO);
        order.setStatus(Order.OrderStatus.COMPLETED);
        orderRepository.save(order);
        sendNotification(OrderMapper.toOrderDTO(order));
        log.info("Notification sent and order {} status {}", order.getId(), order.getStatus());
    }

    private void sendNotification(OrderDTO orderDTO) {
        log.info("Sending notification to user {} about order: {}", orderDTO.getUserId(), orderDTO.getId());
    }
}
