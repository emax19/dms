package com.dms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@EnableScheduling
@SpringBootApplication
public class DeviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceApplication.class, args);
    }

    @Bean
    public CommandLineRunner clr(OAuth2RestTemplate restTemplate) {
        return (args) -> System.out.println(restTemplate.getAccessToken());

    }

}
