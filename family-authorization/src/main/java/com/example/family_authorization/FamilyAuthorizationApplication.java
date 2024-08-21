package com.example.family_authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FamilyAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilyAuthorizationApplication.class, args);
	}

}
