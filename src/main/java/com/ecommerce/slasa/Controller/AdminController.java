package com.ecommerce.slasa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.slasa.DTO.LoginDto;
import com.ecommerce.slasa.DTO.LoginResponse;
import com.ecommerce.slasa.DTO.RegisterDto;
import com.ecommerce.slasa.Enums.Roles;
import com.ecommerce.slasa.Service.UserService;
import com.ecommerce.slasa.Utility.Response;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	private ResponseEntity<Response<?>> RegisterAdmin(@RequestBody RegisterDto user){
		return ResponseEntity.ok(userService.registerUser(user, Roles.ADMIN));
		
	}
	
	@GetMapping("/Login")
	private  ResponseEntity<?> LoginAdmin(@RequestBody LoginDto user){
		LoginResponse response=userService.LoginData(user,Roles.ADMIN);
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
	
	 @PostMapping("/user/forgot-password")
		private ResponseEntity<?> UpdatePassword(@RequestBody LoginDto updateDetails){
			Response<Object> res=new Response<>();
			if(updateDetails==null) {
				ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(false,"please enter required information"));
			}
			
			res=userService.ForgotPassword(updateDetails,Roles.ADMIN);
			return ResponseEntity.ok(res);
		}
	 
	
}
