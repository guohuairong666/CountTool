package com.urun.counttool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "com.urun.counttool.dao")
@EnableTransactionManagement
public class CountToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountToolApplication.class, args);
    }
}
