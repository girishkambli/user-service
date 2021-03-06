package com.ing.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication
            .run(UserServiceApplication.class, args);
    }
}
