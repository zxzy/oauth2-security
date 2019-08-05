package com.gf.oauth2.hi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiApplication.class,args);
    }
}
