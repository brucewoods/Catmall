package com.hongchai.catmall.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CatmallThirdpartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatmallThirdpartyApplication.class, args);
    }

}
