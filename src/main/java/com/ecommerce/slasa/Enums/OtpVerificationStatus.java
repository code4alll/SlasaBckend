package com.ecommerce.slasa.Enums;

public enum OtpVerificationStatus {
	VERIFIED,
	INVALID,
	EXPIRED;
	 public static OtpVerificationStatus fromString(String value) {
	        if (value == null) {
	            throw new IllegalArgumentException("Value must not be null");
	        }

	        try {
	            return OtpVerificationStatus.valueOf(value.toUpperCase());
	        } catch (IllegalArgumentException e) {
	            throw new IllegalArgumentException("No enum constant " + OtpVerificationStatus.class.getCanonicalName() + "." + value, e);
	        }
	    }

}
