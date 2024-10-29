package com.exercise.publicapi;

import com.exercise.publicapi.config.ServicesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.exercise.publicapi", "com.exercise.publicapi.config"})
@ComponentScan(basePackages = {"com.exercise.publicapi", "com.exercise.config"})
@EnableConfigurationProperties(ServicesConfig.class)

public class PublicapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublicapiApplication.class, args);
    }
}
