package com.makiia.cars_services;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CarsServicesApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarsServicesApplication.class, args);
	}
}
