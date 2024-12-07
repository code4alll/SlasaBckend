package com.ecommerce.slasa.Config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).select()
//                .apis(RequestHandlerSelectors.basePackage("com.Ulink.Ecommerce.Controllers")) // Change this to your controller package

				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
		
	}

	private ApiInfo getInfo() {
		// TODO Auto-generated method stub
		return new ApiInfo("Slasa", "This Project is Developed by Aditi Ranjan for Online Shopping", "1.0", "Terms of Service", new Contact("Dheeraj","","dheeraj@ulinkit.com"), "License of APIs", "Api license url",Collections.emptyList());
	}
}

