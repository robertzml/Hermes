package com.shengdangjia.hermeslog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HermesLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(HermesLogApplication.class, args);
    }

}
