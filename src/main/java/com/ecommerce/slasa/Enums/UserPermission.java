package com.ecommerce.slasa.Enums;

public enum UserPermission {
    USER_MANAGEMENT,
    ORDER_MANAGEMENT,
    INVENTORY_MANAGEMENT,
    FINANCIAL_MANAGEMENT,
    MARKETING_TOOLS,
    ANALYTICS_AND_REPORTING;
	
	 public static UserPermission fromString(String value) {
	        if (value == null) {
	            throw new IllegalArgumentException("Value must not be null");
	        }

	        try {
	            return UserPermission.valueOf(value.toUpperCase());
	        } catch (IllegalArgumentException e) {
	            throw new IllegalArgumentException("No enum constant " + UserPermission.class.getCanonicalName() + "." + value, e);
	        }
	    }
}
