package com.msb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication/*(scanBasePackages = {"com.msb"})*///可以扫描其他包下的com.msb
@MapperScan("com.msb.dongbao.ums.mapper")
public class MsbDongbaoPortalWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsbDongbaoPortalWebApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
