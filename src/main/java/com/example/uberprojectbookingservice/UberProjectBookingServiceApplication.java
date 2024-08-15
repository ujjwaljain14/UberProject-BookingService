package com.example.uberprojectbookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan("com.example.uberprojectentityservice.models")
public class UberProjectBookingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UberProjectBookingServiceApplication.class, args);
    }

}
