package com.geekbrains.spring.web.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class WebAuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebAuthServiceApplication.class, args);
    }
}
