package com.meiqi.edu;

import com.aliyun.oss.OSS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @Author Monica
 * @Date 2022/8/2 14:10
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.meiqi")
@EnableDiscoveryClient
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
    }
}
