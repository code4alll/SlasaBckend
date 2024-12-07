package com.ecommerce.slasa.Config.SecurityConfig;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ecommerce.slasa.Enums.Roles;
import com.ecommerce.slasa.Enums.Status;
import com.ecommerce.slasa.Model.UserModel;
import com.ecommerce.slasa.Repository.UserRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
@Component
public class JwtTokenUtil {
	  @Value("${jwt.secret}") // Replace with your secret key from properties or configuration
	    private String jwtSecret;

	    @Value("${jwt.expiration}") // Replace with your preferred token expiration time
	    private int jwtExpirationMs;
	    
	    @Autowired
        UserRepo userRepo;
	    public String generateToken(UserModel userDetails) {
	    	
	    	String name = userDetails.getFirstname()+" "+userDetails.getLastname();
	    	String role = userDetails.getRole().getRoleName();
	        String email = userDetails.getEmail();
	        String userName=userDetails.getUsername();
	        String country=userDetails.getCountry();
	       
;	        
	                
	    	
	    	String token = Jwts.builder()
	    			
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
	                .signWith(SignatureAlgorithm.HS512, jwtSecret)
	                .claim("Role", role)
	                .claim("Email", email)
	                .claim("name", name)
	                .claim("username",userName)
	                .claim("country",country)
	                .compact();
	        
	        // Log the generated token
	    String str=token;
	        
	        return token;
	    }


	    public boolean validateToken(String token) {
	        try {
	            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
	            return true;
	        } catch (SignatureException ex) {
	            // Log token signature error
	        } catch (MalformedJwtException ex) {
	            // Log malformed token error
	        } catch (ExpiredJwtException ex) {
	            // Log expired token error
	        } catch (UnsupportedJwtException ex) {
	            // Log unsupported token error
	        } catch (IllegalArgumentException ex) {
	            // Log illegal argument token error
	        }
	        return false;
	    }

	    public Claims decodeToken(String token) {
	        try {
	            return Jwts.parser()
	                    .setSigningKey(jwtSecret)
	                    .parseClaimsJws(token.replace("Bearer ", ""))
	                    .getBody();
	        } catch (Exception ex) {
	            // Log any exceptions that occur during token parsing
	            ex.printStackTrace();
	            return null;
	        }
	    }


		public UserModel getUser(String token) {
		String userName=(String) decodeToken(token).get("users");
		Roles role= Roles.fromString((String)decodeToken(token).get("role"));
		UserModel user=new UserModel();
		if(userName!=null) {
			try {
				 user=userRepo.findByUsernameAndRoleAndIsVerified(userName, role,Status.ACTIVE).orElse(null);

			}
			catch(Exception e){
				e.printStackTrace();
				
			}
		}
		
			return user;
		}
		
		
		public LocalDateTime extractExpiryTime(String token) {
	        try {
	            Claims claims = Jwts.parser()
	                    .setSigningKey(jwtSecret)
	                    .parseClaimsJws(token)
	                    .getBody();
	            Date expiration = claims.getExpiration();
	            return LocalDateTime.ofInstant(expiration.toInstant(), ZoneId.systemDefault());
	        } catch (Exception e) {
	            // Log the exception if needed
	            return null;
	        }
	    }

}
