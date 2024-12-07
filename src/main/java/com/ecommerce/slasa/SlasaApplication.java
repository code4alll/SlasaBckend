package com.ecommerce.slasa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class,org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration.class})

public class SlasaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlasaApplication.class, args);
	}

}
