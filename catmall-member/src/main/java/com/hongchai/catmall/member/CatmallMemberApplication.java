package com.hongchai.catmall.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.hongchai.catmall.member.dao")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.hongchai.catmall.member.feign")
public class CatmallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatmallMemberApplication.class, args);
    }

}
