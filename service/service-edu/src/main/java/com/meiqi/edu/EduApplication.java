package com.meiqi.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author Monica
 * @Date 2022/7/27 15:14
 **/

@SpringBootApplication
@ComponentScan(basePackages = "com.meiqi")
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
