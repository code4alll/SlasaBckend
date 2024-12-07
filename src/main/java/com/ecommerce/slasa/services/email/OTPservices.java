package com.ecommerce.slasa.services.email;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.ecommerce.slasa.Enums.DetailsUpdateType;
import com.ecommerce.slasa.Enums.OtpVerificationStatus;
import com.ecommerce.slasa.Utility.Response;


@Service
@SuppressWarnings("rawtypes")
public class OTPservices {
	
    public static final Map<String, OTPDetails> otpMap = new ConcurrentHashMap<>(); 
    public static final Map<String, Map<DetailsUpdateType,OTPDetails>> userVerification = new ConcurrentHashMap<>();
    

    
    public static OtpVerificationStatus verifyOtp(String email,String otp) {
        OTPDetails otpDetails = otpMap.get(email.toLowerCase());
        
        if (otpDetails != null && otpDetails.getOtp().equals(otp)) {
            // Check if OTP is expired
            if (otpDetails.getCreationTime().plusMinutes(10).isBefore(LocalDateTime.now())) {
                return OtpVerificationStatus.EXPIRED;
            }

            // OTP verification successful
            return OtpVerificationStatus.VERIFIED;
        } else {
            // Invalid OTP
            return OtpVerificationStatus.INVALID;
        }
    }
    
    public OtpVerificationStatus verifyOtp(OTPDetails otpDetails,String otp) {
        
        if (otpDetails != null && otpDetails.getOtp().equals(otp)) {
            // Check if OTP is expired
            if (otpDetails.getCreationTime().plusMinutes(10).isBefore(LocalDateTime.now())) {
                return OtpVerificationStatus.EXPIRED;
            }

            // OTP verification successful
            return OtpVerificationStatus.VERIFIED;
        } else {
            // Invalid OTP
            return OtpVerificationStatus.INVALID;
        }
    }
    
    public static String saveOtpDetails(DetailsUpdateType type,String u,String data) {
    	String username=u.toLowerCase();
    	String otp=generateOTP(6);
    	if(userVerification.containsKey(username)) {
    		Map<DetailsUpdateType,OTPDetails> details=userVerification.get(username);
    		details.put(type,new OTPDetails(otp,LocalDateTime.now(),data));
        	return otp;

    	}else {
    		Map<DetailsUpdateType,OTPDetails> details=new HashMap<>();
    		details.put(type, new OTPDetails(otp,LocalDateTime.now(),data));
        	userVerification.put(username,details);
        	return otp;


    	}
    }
    
    public String saveOtpDetails(String username) {
    	String otp=generateOTP(6);
    	otpMap.put(username.toLowerCase(),new OTPDetails(otp,LocalDateTime.now(),null));
    	return otp;
    }
    
    

    
    public static String generateOTP(int length) {  
	    String numbers = "0123456789";  
	    Random rndm_method = new Random();  
	    char[] otpArray = new char[length];  
	    for (int i = 0; i < length; i++) {  
	    	otpArray[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));  
	    }  
	    String otp=new String(otpArray);  
	    return otp;
	} 
   
	public static class OTPDetails {
        private final String otp;
        private final LocalDateTime creationTime;
        private final String data;

        public OTPDetails(String otp, LocalDateTime creationTime, String data) {
			super();
			this.otp = otp;
			this.creationTime = creationTime;
			this.data = data;
		}



        public String getData() {
			return data;
		}



		public String getOtp() {
            return otp;
        }

        public LocalDateTime getCreationTime() {
            return creationTime;
        }
    }
	
	public static class UserVerificationDetails{
		private final String username;
		private final Boolean isVerifed;
		
		public String getUsername() {
			return username;
		}
		public Boolean getIsVerifed() {
			return isVerifed;
		}
		public UserVerificationDetails(String username, Boolean isVerifed) {
			super();
			this.username = username;
			this.isVerifed = isVerifed;
		}
		
		
	}

	@SuppressWarnings("unchecked")
	public Response verifyVerificationOtp(String otp, String type, String username) {
		
		if(!userVerification.containsKey(username.toLowerCase())) {
			return new Response(false,"user details not found !!");
		}
		Map<DetailsUpdateType,OTPDetails> details=userVerification.get(username.toLowerCase());
		if(details==null) {
			return new Response(false,"user details not found !!");

		}
		DetailsUpdateType detailsUpdateType;
        try {
        	detailsUpdateType = DetailsUpdateType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return new Response(false, "Invalid VerificationType select any one of :{ "+DetailsUpdateType.PASSWORD.toString()+" , "+DetailsUpdateType.USERNAME.toString()+" , "+DetailsUpdateType.EMAIL.toString()+" }");
        }
		
		if(!details.containsKey(detailsUpdateType)) {
			return new Response(false,"no maching data to update");

		}
		OTPDetails detail=details.get(detailsUpdateType);
		
		if(detail!=null) {
			OtpVerificationStatus res=this.verifyOtp(detail, otp);
			if(res.equals(OtpVerificationStatus.VERIFIED)) {
			return new Response(true,res.toString(),detail.getData());}
			else {
				return new Response(false,res.toString());}

			}

		
		
		return null;
	}

	public void InvalidateOtp(String type, String username) {
		if(!userVerification.containsKey(username.toLowerCase())) {
			return;
		}
		Map<DetailsUpdateType,OTPDetails> details=userVerification.get(username.toLowerCase());
		if(details==null) {
			return;

		}
		
		if(!details.containsKey(DetailsUpdateType.fromString(type))) {
			return;

		}
		details.remove(DetailsUpdateType.fromString(type));
		if(userVerification.containsKey(username.toLowerCase())&&userVerification.get(username.toLowerCase())==null) {
			userVerification.remove(username.toLowerCase());
		}
		
		
		
	}
	
	
	 public void cleanExpiredOTPs() {
	        LocalDateTime now = LocalDateTime.now();

	        // Clean userVerification map
	        Iterator<Map.Entry<String, Map<DetailsUpdateType, OTPDetails>>> userIterator = userVerification.entrySet().iterator();

	        while (userIterator.hasNext()) {
	            Map.Entry<String, Map<DetailsUpdateType, OTPDetails>> userEntry = userIterator.next();
	            Map<DetailsUpdateType, OTPDetails> otpDetailsMap = userEntry.getValue();

	            Iterator<Map.Entry<DetailsUpdateType, OTPDetails>> otpIterator = otpDetailsMap.entrySet().iterator();

	            while (otpIterator.hasNext()) {
	                Map.Entry<DetailsUpdateType, OTPDetails> otpEntry = otpIterator.next();
	                OTPDetails otpDetails = otpEntry.getValue();

	                // Assuming the OTP expires after a certain duration (e.g., 5 minutes)
	                if (otpDetails.getCreationTime().plusMinutes(5).isBefore(now)) {
	                    otpIterator.remove(); // Remove the expired OTP entry
	                }
	            }

	            if (otpDetailsMap.isEmpty()) {
	                userIterator.remove(); // Remove the user entry if no OTPs remain
	            }
	        }

	        // Clean otpMap
	        Iterator<Map.Entry<String, OTPDetails>> otpMapIterator = otpMap.entrySet().iterator();

	        while (otpMapIterator.hasNext()) {
	            Map.Entry<String, OTPDetails> otpMapEntry = otpMapIterator.next();
	            OTPDetails otpDetails = otpMapEntry.getValue();

	            // Assuming the OTP expires after a certain duration (e.g., 5 minutes)
	            if (otpDetails.getCreationTime().plusMinutes(5).isBefore(now)) {
	                otpMapIterator.remove(); // Remove the expired OTP entry
	            }
	        }
	    }
	
	

}
