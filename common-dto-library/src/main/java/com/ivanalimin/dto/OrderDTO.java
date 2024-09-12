package com.ivanalimin.dto;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class OrderDTO {
    private UUID id;
    private String userId;
    private BigDecimal amount;
    private String status;

    public OrderDTO() {
    }

    public OrderDTO(UUID id, String userId, BigDecimal amount, String status) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO)) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(id, orderDTO.id)
                && Objects.equals(userId, orderDTO.userId)
                && Objects.equals(amount, orderDTO.amount)
                && Objects.equals(status, orderDTO.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, amount, status);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
