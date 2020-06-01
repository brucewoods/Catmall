package com.hongchai.catmall.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.hongchai.catmall.order.dao")
public class CatmallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatmallOrderApplication.class, args);
    }

}
