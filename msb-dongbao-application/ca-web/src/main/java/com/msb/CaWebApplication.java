package com.msb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CaWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaWebApplication.class, args);
    }

    @GetMapping("/ca")
    public static String hi(){
        return "hi-https";
    }
}
