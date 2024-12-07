package com.ecommerce.slasa.Enums;


public enum PaymentStatus {
	SUCCESS,
    FAILED,
    PENDING,
    CANCELLED,
    CREATED,
    APPROVED,
    UNDEFINED,
    REFUNDED;
	 public static PaymentStatus fromString(String value) {
	        if (value == null) {
	            throw new IllegalArgumentException("Value must not be null");
	        }

	        try {
	        	if(value.equalsIgnoreCase("captured")) {
	        		return PaymentStatus.SUCCESS;
	        	}
	            return PaymentStatus.valueOf(value.toUpperCase());
	        } catch (IllegalArgumentException e) {
	        	return PaymentStatus.UNDEFINED;
//	            throw new IllegalArgumentException("No enum constant " + PaymentStatus.class.getCanonicalName() + "." + value, e);
	        }
	    }
}

