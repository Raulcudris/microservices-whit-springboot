package com.makiia.users_services;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UsersServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersServicesApplication.class, args);
	}

}
