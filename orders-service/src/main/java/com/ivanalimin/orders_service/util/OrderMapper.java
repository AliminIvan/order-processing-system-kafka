package com.ivanalimin.orders_service.util;

import com.ivanalimin.orders_service.dto.OrderDTO;
import com.ivanalimin.orders_service.model.Order;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderMapper {
    public static OrderDTO toOrderDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getUserId(),
                order.getAmount(),
                order.getStatus().name()
        );
    }

    public static Order toOrderEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setUserId(orderDTO.getUserId());
        order.setAmount(orderDTO.getAmount());
        order.setStatus(Order.OrderStatus.valueOf(orderDTO.getStatus()));
        return order;
    }
}
