package com.hongchai.catmall.search.config;


import org.apache.http.HttpHost;
import org.elasticsearch.client.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatmallElasticsearchConfig {


    public static final RequestOptions COMMON_OPTIONS;
    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
//        builder.addHeader("Authorization", "Bearer " + TOKEN);
//        builder.setHttpAsyncResponseConsumerFactory(
//                new HttpAsyncResponseConsumerFactory
//                        .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }
    @Bean
    public RestHighLevelClient   esClient(){

        RestClientBuilder builder=null;
        builder= RestClient.builder(new HttpHost("192.168.146.146",9200,"http"));
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(builder);
        return  restHighLevelClient;
    }
}
