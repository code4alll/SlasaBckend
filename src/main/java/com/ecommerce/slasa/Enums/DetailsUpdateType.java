package com.ecommerce.slasa.Enums;

public enum DetailsUpdateType {
	EMAIL,
	PHONE,
	PASSWORD,
	USERNAME,
	PROFILE;
	
	 public static DetailsUpdateType fromString(String value) {
	        if (value == null) {
	            throw new IllegalArgumentException("Value must not be null");
	        }

	        try {
	            return DetailsUpdateType.valueOf(value.toUpperCase());
	        } catch (IllegalArgumentException e) {
	            throw new IllegalArgumentException("No enum constant " + DetailsUpdateType.class.getCanonicalName() + "." + value, e);
	        }
	    }
	

}