package com.hongchai.catmall.ware.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("com.hongchai.catmall.ware.dao")
public class MyBatisConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor=new PaginationInterceptor();

        paginationInterceptor.setOverflow(true); // out of size, direct to index

        paginationInterceptor.setLimit(1000); //set max size request

        return  paginationInterceptor;
    }
}
