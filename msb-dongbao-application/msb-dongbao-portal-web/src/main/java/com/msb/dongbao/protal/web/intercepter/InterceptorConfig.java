package com.msb.dongbao.protal.web.intercepter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user-member/register")
				.excludePathPatterns("/user-member/login")
				.excludePathPatterns("/code/generator");
    }
    @Bean
    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }
}
