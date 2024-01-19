package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RestaurantV2Application {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantV2Application.class, args);
    }
}
