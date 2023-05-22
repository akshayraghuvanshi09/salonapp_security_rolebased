package com.salon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ComponentScan({"com.bezkoder.springjwt","com.salon"})
@SpringBootApplication
public class SalonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalonServiceApplication.class, args);
	}

//	  @Bean
//	  public PasswordEncoder passwordEncoder() {
//	    return new BCryptPasswordEncoder();
//	  }

}
