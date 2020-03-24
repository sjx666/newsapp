package com.zxtt.newsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import tk.mybatis.spring.annotation.MapperScan;


@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication(scanBasePackages = "com.zxtt.newsapp")
@MapperScan("com.zxtt.newsapp.commons.mapper")
public class NewsappApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsappApplication.class, args);
    }

}
