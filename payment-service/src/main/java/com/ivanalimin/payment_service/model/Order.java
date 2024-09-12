package com.ivanalimin.payment_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private UUID id;
    private String userId;
    private BigDecimal amount;
    private OrderStatus status;

    public enum OrderStatus {
        NEW, IN_PROGRESS, COMPLETED, CANCELLED, PAYED
    }
}
