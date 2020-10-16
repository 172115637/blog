package com.zjjhyzd.blog.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zjjhyzd.blog.entity.OSSClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliyunOSSConfiguration {


    @Autowired
    OSSClientProperties ossClientProperties;

    @Bean(destroyMethod = "shutdown")
    public OSS ossClient() {
        return new OSSClientBuilder().build(ossClientProperties.getEndpoint(), ossClientProperties.getAccessKey(), ossClientProperties.getSecretKey());
    }

}
