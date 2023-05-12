package com.example.mualakabashi_simplenewsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MualaKabashiSimpleNewsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MualaKabashiSimpleNewsApiApplication.class, args);
    }

}
