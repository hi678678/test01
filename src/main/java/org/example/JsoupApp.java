package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//主启动
@MapperScan("org.example.mapper")//扫描mapper层
public class JsoupApp {
    public static void main(String[] args) {
        SpringApplication.run(JsoupApp.class);

    }
}
