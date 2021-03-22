package com.victor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;


@SpringBootApplication(scanBasePackages = "com.victor.back")
public class BackApplication {

    public static void main(String[] args)  {

        ApiContextInitializer.init();
        SpringApplication.run(BackApplication.class, args);
    }

}

