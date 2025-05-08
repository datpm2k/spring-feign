package com.tad.springfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpringFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFeignApplication.class, args);
    }

}
