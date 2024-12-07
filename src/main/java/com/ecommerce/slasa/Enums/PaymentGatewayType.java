package com.ecommerce.slasa.Enums;

public enum PaymentGatewayType {
	RAZORPAY,
    PAYPAL;
	
	 public static PaymentGatewayType fromString(String value) {
	        if (value == null) {
	            throw new IllegalArgumentException("Value must not be null");
	        }

	        try {
	            return PaymentGatewayType.valueOf(value.toUpperCase());
	        } catch (IllegalArgumentException e) {
	            throw new IllegalArgumentException("No enum constant " + PaymentGatewayType.class.getCanonicalName() + "." + value, e);
	        }
	    }

}

