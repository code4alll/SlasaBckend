package com.ecommerce.slasa.Enums;

public enum PaymentMethodType {
	
	CARD,
	UPI,
	BANK;
	public static PaymentMethodType fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null");
        }

        try {
            return PaymentMethodType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("No enum constant " + PaymentMethodType.class.getCanonicalName() + "." + value, e);
        }
    }

}

