package com.example.demosecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class DemosecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemosecurityApplication.class, args);
    }


}
