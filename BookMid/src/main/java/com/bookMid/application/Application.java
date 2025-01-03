package com.bookMid.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ServletComponentScan("com.BookMid")
@Configuration
@ComponentScan("com.BokMid")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
