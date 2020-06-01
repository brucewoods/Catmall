package com.hongchai.catmall.product;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hongchai.catmall.product.entity.BrandEntity;
import com.hongchai.catmall.product.service.BrandService;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
class CatmallProductApplicationTests {
    @Autowired
    OSSClient  ossClient;
    @Autowired
    BrandService brandService;
    @Test
    void testUpload() throws FileNotFoundException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = "oss-cn-shanghai.aliyuncs.com";
//// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
//        String accessKeyId = "LTAI4GKWqEppDy45jpLNz698";
//        String accessKeySecret = "pZ3iuEGbxRfHOAJVLzGYlGrzEtB3kb";
//        String bucketName = "mallcat";
//// <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
//        String objectName = "me.jpg";

// 创建OSSClient实例。
   //     OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        InputStream is=new FileInputStream("C:\\Users\\bruce\\Pictures\\Camera Roll\\WIN_20200531_15_32_21_Pro.jpg");
        ossClient.putObject("mallcat", "test.jpg",is);

// 关闭OSSClient。
        ossClient.shutdown();

        System.out.println("uploaded。。。。。。。。。。。");
    }
    @Test
    void contextLoads() {

//        brandService.save(new BrandEntity(){
//            @Override
//            public void setName(String name) {
//                super.setName("apple");
//            }
//        });
     var brand =new BrandEntity();
//        brand.setName("Apple");
//        brand.setDescript("Description");
//        brandService.save(brand);
//
//        System.out.println("save success");
         brand.setBrandId(1L);
         brand.setDescript("apple is in U.S.");
         brandService.updateById(brand);
        List<BrandEntity> list= brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id",1));
        System.out.println("print search result::");
          list.forEach(o->System.out.println(o.getName()));

        }

    }


