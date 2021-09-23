package com.msb.dongbao.protal.web.intercepter;

import com.msb.dongbao.common.base.TokenException;
import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.msb.dongbao.common.utill.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入了拦截器");
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)){
            throw new TokenException("token为空");
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(TokenCheck.class)){
            TokenCheck annotation = method.getAnnotation(TokenCheck.class);
            if (annotation.required()){
                try {
                    JwtUtil.ParseToken(token);
                    return true;
                } catch (Exception e) {
                    throw new TokenException("token异常");
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
