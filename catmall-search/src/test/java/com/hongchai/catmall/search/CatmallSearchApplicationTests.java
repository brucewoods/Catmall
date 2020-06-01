package com.hongchai.catmall.search;

import com.alibaba.fastjson.JSON;
import com.hongchai.catmall.search.config.CatmallElasticsearchConfig;
import lombok.Data;
import lombok.var;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonbTester;

import java.io.IOException;

@SpringBootTest
class CatmallSearchApplicationTests {


    @Qualifier("esClient")
    @Autowired
    RestHighLevelClient client;
    @Test
    void contextLoads() {
        System.out.println(client);
    }

    @Test
    void searchData() throws IOException {
        SearchRequest searchRequest=new SearchRequest();
        var s=searchRequest.indices("bank");
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        var r=s.source(builder);

        SearchResponse response=client.search(r, RequestOptions.DEFAULT);
        SearchHits  hits=response.getHits();
        var t=hits.getTotalHits().value;

        if(t<0) return;
        for (var hit:hits
             ) {

           var sc =hit.getSourceAsMap();
           System.out.println(sc.get("firstname"));

        }

    }
    @Test
    void doIndex() throws IOException {

        IndexRequest  ir=new IndexRequest("users");
        ir.id("1");
//        ir.source("userName","bruce","age",33,"gender","male");
//
        User user=new User();
        user.setAge(11);
        user.setGender("男");
        user.setUserName("bruce");
        var j= JSON.toJSONString(user);
        ir.source(j, XContentType.JSON);
        IndexResponse irp=client.index(ir,CatmallElasticsearchConfig.COMMON_OPTIONS);

        System.out.println(irp);
    }

    @Data
    class User{
        private  String userName;
        private int age;
        private String gender;
    }

}
