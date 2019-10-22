package com.marcin.Facebook;

import com.marcin.Facebook.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class FacebookApplication {

	public static void main(String[] args) {

		SpringApplication.run(FacebookApplication.class, args);
	}

}
