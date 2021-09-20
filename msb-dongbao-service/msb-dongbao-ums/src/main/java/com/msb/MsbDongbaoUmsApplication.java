package com.msb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.msb.msbdongbaoums.mapper")
public class MsbDongbaoUmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsbDongbaoUmsApplication.class, args);
    }

}
