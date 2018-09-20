package com.towerchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.towerchain.appPayment")
@EnableAutoConfiguration
public class AppPaymentApplication {

    public static void main(String[] args) {
        Class<?>[] primarySources = {AppPaymentApplication.class};
        SpringApplication.run(primarySources, args);
    }
}
