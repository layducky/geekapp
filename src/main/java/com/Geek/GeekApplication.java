package com.Geek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@SpringBootApplication
public class GeekApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeekApplication.class, args);
    }

}