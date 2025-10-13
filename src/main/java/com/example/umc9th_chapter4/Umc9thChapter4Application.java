package com.example.umc9th_chapter4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Umc9thChapter4Application {

    public static void main(String[] args) {
        SpringApplication.run(Umc9thChapter4Application.class, args);
    }

}
