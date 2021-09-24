package com.msb.dongbao.protal.web.advice;

import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import com.msb.dongbao.common.base.TokenException;
import com.msb.dongbao.common.base.result.ResultWrapper;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.LoginException;

/*@ControllerAdvice
@RestController*/
//上述两个可以用restcontrollerAdvice来代替
@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(ArithmeticException.class)
    public ResultWrapper customException(){
        return ResultWrapper.builder().code(301).message("统一异常处理").build();
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(TokenException.class)
    public ResultWrapper loginException(Exception e){
        return ResultWrapper.getFailBuilder().message(e.getMessage()).build();
    }

    //验证码统一异常处理，kaptchaController中的verify中异常
    @org.springframework.web.bind.annotation.ExceptionHandler(value = KaptchaException.class)
    public String kaptchaExceptionHandler(KaptchaException kaptchaException) {
        if (kaptchaException instanceof KaptchaIncorrectException) {
            return "验证码不正确";
        } else if (kaptchaException instanceof KaptchaNotFoundException) {
            return "验证码未找到";
        } else if (kaptchaException instanceof KaptchaTimeoutException) {
            return "验证码过期";
        } else {
            return "验证码渲染失败";
        }

    }
}
