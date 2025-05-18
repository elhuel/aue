package org.example.coworking_rent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CoworkingRentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoworkingRentApplication.class, args);
	}
}
