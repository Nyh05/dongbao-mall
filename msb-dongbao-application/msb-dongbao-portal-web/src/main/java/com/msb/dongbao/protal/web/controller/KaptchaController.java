package com.msb.dongbao.protal.web.controller;

import com.baomidou.kaptcha.Kaptcha;
import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.msb.dongbao.protal.web.custom.MyGoogleKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {
    @Autowired
    private Kaptcha kaptcha;
    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request, HttpServletResponse response){

        kaptcha.render();
        //想要存储在redis中就重写render中的方法，在custom中的MyGoolekaptcha类里
    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    //使用全局异常处理，在ExceptionHandler中做统一异常处理
    public String verify(String verifyCode, HttpServletRequest request){
/*        boolean validate = false;
        try {
            validate = kaptcha.validate(verifyCode,20);//设置了存在的时间
            if (validate){
                return "验证码校验通过";
            }
        } catch (Exception e) {
        }
        return "验证码校验不通过";*/
            //对异常做统一处理
            boolean validate = kaptcha.validate(verifyCode,20);//设置了存在的时间
            if (validate){
                return "验证码校验通过";
            }
            return "验证码校验不通过";

    }



    @Autowired
    MyGoogleKaptcha myGoogleKaptcha;
    @GetMapping("/generator-my")
    @TokenCheck(required = false)
    public void generatorCodeMy(HttpServletRequest request, HttpServletResponse response){

        myGoogleKaptcha.render();
        //想要存储在redis中就重写render中的方法，在custom中的MyGoolekaptcha类里
    }

    @GetMapping("/verify-my")
    @TokenCheck(required = false)
    public String verifyMy(String verifyCode, HttpServletRequest request){
        //对异常做统一处理
        boolean validate = myGoogleKaptcha.validate(verifyCode,20);//设置了存在的时间
        if (validate){
            return "验证码校验通过";
        }
        return "验证码校验不通过";

    }
}
