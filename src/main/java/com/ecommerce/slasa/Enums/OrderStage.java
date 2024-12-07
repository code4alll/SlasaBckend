package com.ecommerce.slasa.Enums;


public enum OrderStage {
	CREATED("CONFIRMATION PENDING"),
	CONFIRM("ORDER CONFIRMED"),
    PROCESSING("PROCESSING"),
    READYTOSHIP("READY TO SHIP"),    
    SHIPPED("SHIPPED"),
    ONTHEWAY("ON THE WAY"),
    OUTFORDELIVERY("OUT FOR DELIVERY"),
    DELIVERED("DELEVERED"),
    RETURNEDCREATED("RETURN INITIATED"),
    RETURNCONFIRMED("RETURN CONFIRMED"),
    PICKUPINITIATED("PICKUP INITIATED"),
    PICKUPCOMPLETED("PICKUP COMPLETED"),
    REFUNDINITIATED("REFUND INITIATED"),    
    REFUNDED("REFUNDED"),
    CANCELLED("CANCELLED"),
	PAYMENT_FAILED("PAYMENT FAILED"),
	PAYMENT_PENDING("PAYMENT PENDING");

    private String stage;

    OrderStage(String stage) {
        this.stage = stage;
    }

    public static OrderStage fromString(String stage) {
        for (OrderStage orderStage : OrderStage.values()) {
            if (orderStage.getStage().equalsIgnoreCase(stage)) {
                return orderStage;
            }
        }
        throw new IllegalArgumentException("No enum constant found for stage: " + stage);
    }

    @Override
    public String toString() {
        return stage;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}

