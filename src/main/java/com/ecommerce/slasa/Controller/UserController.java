package com.ecommerce.slasa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.slasa.DTO.LoginDto;
import com.ecommerce.slasa.DTO.LoginResponse;
import com.ecommerce.slasa.DTO.RegisterDto;
import com.ecommerce.slasa.DTO.emailResponseDto;
import com.ecommerce.slasa.Enums.OtpVerificationStatus;
import com.ecommerce.slasa.Enums.Roles;
import com.ecommerce.slasa.Service.UserService;
import com.ecommerce.slasa.Utility.Response;
import com.ecommerce.slasa.services.email.OTPservices;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/user/signup")
	private ResponseEntity<Response<?>> RegisterUser(@RequestBody RegisterDto user){
		return ResponseEntity.ok(userService.registerUser(user, Roles.USER));
		
	}
	
	@GetMapping("/user/Login")
	private  ResponseEntity<?> LoginUser (@RequestBody LoginDto user){
		LoginResponse response=userService.LoginData(user,Roles.USER);
		Response<?> res=new Response<>();
		
		
	    
	    if(response.getToken()!=null&&response.getStatus()) {
	    	
	    	return ResponseEntity.status(HttpStatus.OK).body(response);
	    }
	    else if(response.getMessage()!=null&&response.getToken()==null) {
	    	res=new Response<String>(false,response.getMessage(),response.getMessage());
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	    }
	    else {
	    	res=new Response<String>(false,"Server Unavailable","Error while fetching User Information!!");
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	    }
	    
	    
	
}
	
	
	
	

	
	 @PostMapping("/verifyOtp")
	    private ResponseEntity<?> verifyOTP(@RequestParam("username") String username,@RequestParam("otp") String otp,@RequestParam("role") String role){
	    	OtpVerificationStatus response=null;
	    	emailResponseDto res=new emailResponseDto();
	    	if(username!=null&&otp!=null&role!=null) {
	    		
	    		try {
	    			response=OTPservices.verifyOtp(username, otp);
	    			if(response.equals(OtpVerificationStatus.EXPIRED)) {
	    				
	    				res.setEmail(username);
	    				res.setMessage(response.toString());
	    				res.setSuccess(false);
	    	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);

	    			}
	    			else if(response.equals(OtpVerificationStatus.VERIFIED)) {
	    				res.setEmail(username);
	    				res.setSuccess(false);
	    				
	    				if(role!=null&&role.equalsIgnoreCase("USER")) {
	    					if(userService.verifyUser(username)) {
	    						res.setMessage(response.toString());
	    						res.setSuccess(true);
	        	            return ResponseEntity.status(HttpStatus.OK).body(res);}

	    				}else if(role!=null&&role.equalsIgnoreCase("ADMIN")) {
	    					if(userService.verifyAdmin(username)) {
	    						res.setMessage(response.toString());
	    						res.setSuccess(true);
	        	            return ResponseEntity.status(HttpStatus.OK).body(res);}

	    				}
	    				
	    	            return ResponseEntity.status(HttpStatus.CONFLICT).body(res);

	    			

	    			}
	    			else {
	    				res.setEmail(username);
	    				res.setMessage(response.toString());
	    				res.setSuccess(false);
	    	            return ResponseEntity.status(HttpStatus.CONFLICT).body(res);

	    			}
	    			
	    		}catch(Exception e) {
	    			res.setEmail(username);
					res.setMessage("server error");
					res.setSuccess(false);
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);

	    		}
	    	}else {
	    		res.setEmail(username);
				res.setMessage("please check username or otp");
				res.setSuccess(false);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);

	    	}
		
	    }
	 
	 @PostMapping("/logout")
	    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
	        if (token.startsWith("Bearer ")) {
	            token = token.substring(7);
	            userService.logoutUser(token);

	        } 
	        return ResponseEntity.ok("Successfully logged out");
	    }
	 
	 
	 @PostMapping("/user/forgot-password")
		private ResponseEntity<?> UpdatePassword(@RequestBody LoginDto updateDetails){
			Response<Object> res=new Response<>();
			if(updateDetails==null) {
				ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(false,"please enter required information"));
			}
			
			res=userService.ForgotPassword(updateDetails,Roles.USER);
			return ResponseEntity.ok(res);
		}
	 
	 @PostMapping("/forgot-password/verify")
	 private ResponseEntity<?> VerifyOtpForPassword(@RequestParam(name="otp",required=true) String otp,@RequestParam(name="username",required=true)String username,@RequestParam(name="role") Roles role){
			 
		 Response response=userService.VerifyAndUpdatePassword(otp,username,role);
		 
		 return ResponseEntity.ok(response);
	 }

}
	
