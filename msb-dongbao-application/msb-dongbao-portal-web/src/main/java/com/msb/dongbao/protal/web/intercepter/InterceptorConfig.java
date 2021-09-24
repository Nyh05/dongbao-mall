package com.msb.dongbao.protal.web.intercepter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*registry.addInterceptor(authInterceptor())
                .addPathPatterns("/request/**")
                .excludePathPatterns("/user-member/register")
				.excludePathPatterns("/user-member/login")
				//.excludePathPatterns("/code/generator");
				.excludePathPatterns("/code/**");*/
        InterceptorRegistration irn = registry.addInterceptor(authInterceptor());
        irn.addPathPatterns("/**");
        irn.excludePathPatterns("/user-member/register");
        irn.excludePathPatterns("/user-member/login");
        /*irn.excludePathPatterns("/code/generator");
        irn.excludePathPatterns("/code/verify");*/
        irn.excludePathPatterns("/code/**");
        irn.excludePathPatterns("/jacaptcha/**");
        irn.excludePathPatterns("/happy-captcha/**");
        irn.excludePathPatterns("/easy-captcha/**");
    }
    @Bean
    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }
}
