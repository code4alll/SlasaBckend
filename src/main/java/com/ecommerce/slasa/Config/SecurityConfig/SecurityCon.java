
package com.ecommerce.slasa.Config.SecurityConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityCon extends WebSecurityConfigurerAdapter {


    private final JwtTokenUtil jwtTokenProvider;
    private final JwtBlacklistService jwtBlacklistService;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;




    @Value("${jwt.secret}") // Injects the value of jwt.secret from application properties
    private String jwtSecret;

    public SecurityCon(JwtTokenUtil jwtTokenProvider,JwtBlacklistService jwtBlacklistService,CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtBlacklistService=jwtBlacklistService;
        this.customAccessDeniedHandler=customAccessDeniedHandler;
    }




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors() // Enable CORS globally
            .and()
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/Login").permitAll()
                .antMatchers("/api/admin/**").permitAll()
                .antMatchers("/api/register","/api/user/**","/api/autocomplete","/api/category/autocomplete","/api/category/getproducts","/api/login/google","/api/login/google/callback/**","/api/paypal/**","/api/payment/**","/api/get-currency-rates","/api/sitemap.xml","/api/assign-notification/**","/api/forgot-password/verify").permitAll()
                .antMatchers("/api/getproducts","/api/verifyOtp","/api/image/**","/api/update-seller-details","/api/product/**","/api/user/doc/**","/api/seller/profile-image/**").permitAll()
                .antMatchers("/swagger-ui/**", "/v3/api-docs/**","/v2/api-docs/**","/api/v1/**" ,"/swagger-resources/**","/webjars/**","/","/health","/api/user/forgot-password","/api/user/forgot-password/verify/**").permitAll() // Permit access to Swagger UI and resources

                .antMatchers("/api/**").authenticated()
//                .antMatchers("/api/AddProduct").authenticated()

                .anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())
            .accessDeniedHandler(customAccessDeniedHandler)

            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().addFilterBefore(new JwtAuthorizationFilter(authenticationManager(), jwtTokenProvider,jwtBlacklistService), BasicAuthenticationFilter.class);
;
    }

    // Used by Spring Security if CORS is enabled.
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }

//    @Override
//    public void configure(WebSecurity web) {
//        // Ignore some endpoints from authentication (e.g., static resources)
//        web.ignoring().antMatchers("/static/**", "/{spring:\\w+}/**{spring:?!(\\.js|\\.css)$}");
//
//    }
    
   
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
//    @Bean
//    public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
//        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
//        expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
//        return expressionHandler;
//    }
}
