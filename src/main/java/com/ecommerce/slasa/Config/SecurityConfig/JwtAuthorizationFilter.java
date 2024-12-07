package com.ecommerce.slasa.Config.SecurityConfig;




import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecommerce.slasa.Enums.Roles;

import io.jsonwebtoken.Claims;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenProvider;
    
    
    private final JwtBlacklistService jwtBlacklistService;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenProvider,JwtBlacklistService jwtBlacklistService) {
        super();
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtBlacklistService = jwtBlacklistService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }else if (jwtBlacklistService.isBlacklisted(header.substring(7))) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Your token is Expired. Please log in again.");
            response.getWriter().flush();
            
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            // Validate the token and extract the username using JwtTokenProvider
            if (jwtTokenProvider.validateToken(token.replace("Bearer ", ""))) {
            	Claims tokenClaims = jwtTokenProvider.decodeToken(token);
            	
            	
                String name = (String)tokenClaims.get("users");
                String user=(String)tokenClaims.get("username");
                String role=(String)tokenClaims.get("Role");
                String country=(String) tokenClaims.get("country");
                
                ClaimedToken claimed=new ClaimedToken();
                
                Roles roleEnum = Roles.fromString(role);
                claimed.setCountry(country);
                claimed.setName(name);
                claimed.setUsername(user);
                
                

             // Create a set of GrantedAuthority
             Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
             authorities.add(new SimpleGrantedAuthority("ROLE_"+roleEnum.toString().toUpperCase()));

                if (user != null) {
                    // Create a simple authentication token with the username
                    return new UsernamePasswordAuthenticationToken(claimed, null, authorities);
                }
            }
        }
        return null;
    }
}
