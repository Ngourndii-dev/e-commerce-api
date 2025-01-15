package com.example.springbootexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.example.springbootexam")
public class SpringbootExamApplication{
    public static void main(String[] args) {
        SpringApplication.run(SpringbootExamApplication.class, args);
    }
}
