package com.example.family_transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FamilyTransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyTransactionApplication.class, args);
    }

}
