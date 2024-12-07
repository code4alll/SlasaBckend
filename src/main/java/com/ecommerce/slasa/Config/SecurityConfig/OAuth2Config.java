package com.ecommerce.slasa.Config.SecurityConfig;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

@Configuration
public class OAuth2Config {

    @Value("${google.clientId}")
    private String clientId;

    @Value("${google.clientSecret}")
    private String clientSecret;

    @Value("${google.redirectUri}")
    private String redirectUri;
    
    


    @Bean
    public OAuth2RestTemplate oauth2RestTemplate() {

        
    	AuthorizationCodeResourceDetails resourceDetails = new AuthorizationCodeResourceDetails();
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(clientSecret);
        resourceDetails.setAccessTokenUri("https://accounts.google.com/o/oauth2/token");
        resourceDetails.setUserAuthorizationUri("https://accounts.google.com/o/oauth2/auth");
        resourceDetails.setScope(Arrays.asList("openid", "profile", "email"));
        resourceDetails.setPreEstablishedRedirectUri(redirectUri);
        resourceDetails.setUseCurrentUri(false);

        return new OAuth2RestTemplate(resourceDetails, new DefaultOAuth2ClientContext());
    }
}

