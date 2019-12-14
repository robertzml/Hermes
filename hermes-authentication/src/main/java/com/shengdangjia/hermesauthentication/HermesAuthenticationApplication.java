package com.shengdangjia.hermesauthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HermesAuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(HermesAuthenticationApplication.class, args);
    }

}
