package com.ecommerce.slasa.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.slasa.Config.SecurityConfig.JwtBlacklistService;
import com.ecommerce.slasa.Config.SecurityConfig.JwtTokenUtil;
import com.ecommerce.slasa.DTO.LoginDto;
import com.ecommerce.slasa.DTO.LoginResponse;
import com.ecommerce.slasa.DTO.RegisterDto;
import com.ecommerce.slasa.Enums.DetailsUpdateType;
import com.ecommerce.slasa.Enums.Roles;
import com.ecommerce.slasa.Enums.Status;
import com.ecommerce.slasa.Model.Admin;
import com.ecommerce.slasa.Model.User;
import com.ecommerce.slasa.Model.UserModel;
import com.ecommerce.slasa.Repository.UserRepo;
import com.ecommerce.slasa.Utility.Response;
import com.ecommerce.slasa.services.email.EmailService;
import com.ecommerce.slasa.services.email.OTPservices;

import io.jsonwebtoken.Claims;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 @Autowired
	    JwtTokenUtil jwtTokenProvider;
	    
	    @Autowired
	    private JwtBlacklistService jwtBlacklistService;
	    
	    @Autowired 
	    private EmailService emailService;
	    
	    @Autowired
	    private OTPservices otpService;
	    

	public Response<?> registerUser(RegisterDto user, Roles role) {
	    // Create a validator only once
	    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Validator validator = factory.getValidator();

	    // Initialize error map
	    Map<String, String> errorMap = new HashMap<>();

	    try {
	    	
	    	
	        // Create user entity based on role
	        UserModel userEntity = userRepo.findByUsernameAndRoleAndIsVerified(user.getEmail(), role, Status.INACTIVE).orElse((role.equals(Roles.USER)) ? 
		            new User(user.getEmail(), user.getPassword(), user.getEmail(), user.getFirstname(), user.getLastname(), null) :
			            new Admin(user.getEmail(), user.getPassword(), user.getEmail(), user.getFirstname(), user.getLastname(), null));
	        		
	        		userEntity.setPassword(user.getPassword());

	        userEntity.setRole(role);

	        // Check if email already exists
	        if (isEmailAlreadyRegistered(userEntity.getEmail(), userEntity.getRole())) {
	            errorMap.put("email", "Email is already registered");
	            return new Response<>(false, "Error while saving", errorMap);
	        }

	        // Validate user entity
	        Set<ConstraintViolation<UserModel>> violations = validator.validate(userEntity);
	        if (!violations.isEmpty()) {
	            // Collect validation errors
	            for (ConstraintViolation<UserModel> violate : violations) {
	                errorMap.put(violate.getPropertyPath().toString(), violate.getMessage());
	            }
	            return new Response<>(false, "Validation errors", errorMap);
	        }

	        // Encode password and save user entity
	        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
	        userEntity.setFlag(true);
	        userEntity.setIsVerified(Status.INACTIVE);
	        userRepo.save(userEntity);
	        
	       Response<?> res=SendVerificationOtp(userEntity.getEmail(), userEntity.getFirstname());
	        if(res!=null&&!res.getStatus()) {
	        	return new Response<>(true, "Error While sending otp", res.getMessage());
	        }
	        

	        return res;

	    } catch (Exception e) {
	        // Log and handle unexpected errors
	        e.printStackTrace();
	        return new Response<>(false, "An unexpected error occurred", null);
	    }
	}

	
	 public boolean isEmailAlreadyRegistered(String email,Roles role) {
	    	
         return (userRepo.existsByEmailRoleAndIsVerified(email,role,Status.ACTIVE)||userRepo.existsByUsernameRoleAndIsVerified(email, role,Status.ACTIVE));
    }


	
	

	public LoginResponse LoginData(LoginDto loginDto,Roles role) {
	    try {
	        // Validate input fields
	        if (loginDto.getUsername() == null) {
	            return new LoginResponse(loginDto.getUsername(), null, false, "Login Failed, Please Enter All required fields (username, password)", null, null, null, null);
	        }

	        if (loginDto.getPassword() == null) {
	            return new LoginResponse(loginDto.getUsername(), null, false, "Login Failed, Please Enter password", null, null, null, null);
	        }

	    

	        // Get role

	        // Find user by username and role
	        UserModel user = userRepo.findByUsernameAndRoleAndIsVerified(loginDto.getUsername(), role,Status.ACTIVE).orElse(null);

	        // Check if user exists and password matches
	        if (user != null) {
	            String password = loginDto.getPassword();
	            String encodedPassword = user.getPassword();
	            boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

	            if (isPwdRight) {
	                // Generate token
	                String token = jwtTokenProvider.generateToken(user);

	                // Initialize the response object
	                LoginResponse res = new LoginResponse();
	                res.setToken(token);
	                res.setStatus(true);
	                res.setMessage("Login Success");
	   
	                res.setFirstname(user.getFirstname());
	                res.setLastname(user.getLastname());
	                res.setEmail(user.getEmail());
	                res.setRole(role);
	               

	             

	                return res;

	            } else {
	                return new LoginResponse(loginDto.getUsername(), null, false, "Invalid Credentials", null, null, null, null);
	            }

	        } else {
	            return new LoginResponse(loginDto.getUsername(), null, false, "User does not exist", null, null, null, null);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return new LoginResponse(loginDto.getUsername(), null, false, "An unexpected error occurred", null, null, null, null);
	    }
	    
	    

	}
	
    public Response SendVerificationOtp(String username,String name) {
		String otp=otpService.saveOtpDetails(username);
		String subject="Slasa Otp Verification";
		String text=name+"_"+"Your Verification OTP".toUpperCase()+otp;
		emailService.sendSimpleMessage(username, subject, text);
		return new Response(true,"verification mail sent to : "+username);
	}


	public boolean verifyUser(String username) {
		try {
		UserModel user=userRepo.findByUsernameAndRoleAndIsVerified(username, Roles.USER,Status.INACTIVE ).orElse(null);
		if(user!=null) {
			user.setFlag(true);
			user.setIsVerified(Status.ACTIVE);
			userRepo.save(user);
			return true;
		}else {
			return userRepo.existsByEmailRoleAndIsVerified(username, Roles.USER, Status.ACTIVE);
		}

	}catch(Exception e) {
		e.printStackTrace();
		return false;
	}
	
	}


	public User getUserDetails(String username) {
		// TODO Auto-generated method stub
		return (User)userRepo.findByUsernameAndRoleAndIsVerified(username, Roles.USER, Status.ACTIVE).orElse(null);
	}
	
	public Admin getAdminDetails(String username) {
		// TODO Auto-generated method stub
		return (Admin)userRepo.findByUsernameAndRoleAndIsVerified(username, Roles.ADMIN, Status.ACTIVE).orElse(null);
	}


	public boolean verifyAdmin(String username) {try {
		UserModel user=userRepo.findByUsernameAndRoleAndIsVerified(username, Roles.ADMIN,Status.INACTIVE ).orElse(null);
		if(user!=null) {
			user.setIsVerified(Status.ACTIVE);
			user.setFlag(true);
			userRepo.save(user);
			return true;
		}
		else {
			return userRepo.existsByEmailRoleAndIsVerified(username, Roles.ADMIN, Status.ACTIVE);
		}

	}catch(Exception e) {
		e.printStackTrace();
		return false;
	}
	}


	public Boolean logoutUser(String token) {
 		
        if (token != null) {
            if (jwtTokenProvider.validateToken(token)) {
            	Claims tokenClaims = jwtTokenProvider.decodeToken(token);
            	
                String user=(String)tokenClaims.get("username");
                String contextUser=this.extractUsernameFromPrincipal(SecurityContextHolder.getContext().getAuthentication());
                if(user!=null&&contextUser!=null&&user.equalsIgnoreCase(contextUser)) {
               	 SecurityContextHolder.getContext().setAuthentication(null);
               	 jwtBlacklistService.addToBlacklist(token);
               	 return true;

                }else {
               	 jwtBlacklistService.addToBlacklist(token);
               	 return true;
                }
                
            }
       	 jwtBlacklistService.addToBlacklist(token);
       	 return true;
	
        }
		return false;
	}
	
	 public  String extractUsernameFromPrincipal(Authentication authentication ) {
	        if (authentication != null && authentication.getPrincipal() instanceof com.ecommerce.slasa.Config.SecurityConfig.ClaimedToken) {
	        	com.ecommerce.slasa.Config.SecurityConfig.ClaimedToken claimedToken = (com.ecommerce.slasa.Config.SecurityConfig.ClaimedToken) authentication.getPrincipal();
	            return claimedToken.getUsername(); // Assuming there's a method to get the username from ClaimedToken
	        }
	        return null; // Return null if principal is not an instance of ClaimedToken or if authentication is null
	    }
	 
	 
		public Response<Object> ForgotPassword(LoginDto updateDetails,Roles role) {
			if(updateDetails.getUsername()==null||updateDetails.getPassword()==null||role==null) {
				return new Response(false,"Please fill all the required fields",updateDetails);
			}
			
			
	        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

			 if (!updateDetails.getPassword().matches(passwordRegex)) {
		           return new Response<>(false,"Wrong Password","Password must be at least 8 characters long, include an uppercase letter, a number, and a special character.");		      
		        }
		
			UserModel user=null;
			if(role.equals(Roles.USER)) {
				user=getUserDetails(updateDetails.getUsername());
				
			}else {
				user=getAdminDetails(updateDetails.getUsername());
			}
			if(user==null) {
				return new Response(false,"user not exist for given user name");
			}else { 
				
			String subject="Slasa Otp Verification";
			 
			
			 String to=user.getEmail();
			 
			 
			 String otp;
				otp = OTPservices.saveOtpDetails(DetailsUpdateType.PASSWORD, user.getUsername(),this.passwordEncoder.encode(updateDetails.getPassword()));
			
			 String text=user.getFirstname()+"_"+"Your password update otp".toUpperCase()+otp;
			 Response emailres=emailService.sendSimpleMessage(to, subject, text);
			 
			 if(emailres.getStatus()) {
				 return new Response<>(true,"otp verification mail sent to: "+user.getEmail());

			 }else {
				 return new Response<>(false,"otp verification mail not sent to: "+user.getEmail(),emailres.getMessage());

			 }
			 }
			
		}


		public Response VerifyAndUpdatePassword(String otp, String username, Roles role) {

			UserModel user=null;
			if(otp==null||username==null||role==null) {
				return new Response(false,"Enter all the required information to verify like username otp role");
			}
		
			if(Roles.USER.equals(role)) {
				user=getUserDetails(username);
			}else {
				user=getAdminDetails(username);
			}
			
			
			if(user==null) {
				return new Response(false,"user not exist");
			}

			
			
			
			Response response=otpService.verifyVerificationOtp(otp,"PASSWORD",user.getUsername());
			if(response.getStatus()&&response.getMessage().equalsIgnoreCase("VERIFIED")) {
				
				
					user.setPassword(response.getData().toString());
				
				SaveUserData(user);
				
				otpService.InvalidateOtp("password",user.getUsername());
				
				return new Response(true,"Otp Verified and "+"password".toUpperCase()+" Updated");

			}
			return response;
		
		}


		private void SaveUserData(UserModel user) {
			user.setFlag(true);
			userRepo.save(user);
			
		}

    //https://herbal-jeevan-9dl6.onrender.com
}
