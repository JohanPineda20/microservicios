package com.ejemplo.microservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroservicioApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioApplication.class, args);
    }

}
