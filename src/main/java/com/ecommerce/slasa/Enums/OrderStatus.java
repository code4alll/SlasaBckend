package com.ecommerce.slasa.Enums;

public enum OrderStatus {
    PLACED("PLACED"),
    CONFIRMED("CONFIRMED"),
    SHIPPED("SHIPED"),
    DELIVERED("DELIVERED"),
    CANCELLED("CANCELLED"),
    RETURNED("RETURNED"),
    REFUNDED("REFUNDED"),
    PENDING("PENDING"),
    PROCESSING("PROCESSING"),
	CREATED("CREATED");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public static OrderStatus fromString(String status) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.getStatus().equalsIgnoreCase(status)) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("No enum constant found for status: " + status);
    }

    @Override
    public String toString() {
        return status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

