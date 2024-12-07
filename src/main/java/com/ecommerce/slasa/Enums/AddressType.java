package com.ecommerce.slasa.Enums;


public enum AddressType {
	    DEFAULT,
	    BILLING,
	    STOCK;
	
	 public static AddressType fromString(String value) {
	        if (value == null) {
	            throw new IllegalArgumentException("Value must not be null");
	        }

	        try {
	            return AddressType.valueOf(value.toUpperCase());
	        } catch (IllegalArgumentException e) {
	            throw new IllegalArgumentException("No enum constant " + AddressType.class.getCanonicalName() + "." + value, e);
	        }
	    }
	

}

