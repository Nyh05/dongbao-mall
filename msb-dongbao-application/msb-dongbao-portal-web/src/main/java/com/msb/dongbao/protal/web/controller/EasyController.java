package com.msb.dongbao.protal.web.controller;

import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/easy-captcha")
public class EasyController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/generator-redis")
    @TokenCheck(required = false)
    public void generatorCodeRedis(HttpServletRequest request,HttpServletResponse response){

        SpecCaptcha specCaptcha = new SpecCaptcha(100, 50);
        String text = specCaptcha.text();
        System.out.println("验证码 = " + text);
        stringRedisTemplate.opsForValue().set("c",text);
        try {
            CaptchaUtil.out(specCaptcha,request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/verify-redis")
    @TokenCheck(required = false)
    public String verifyRedis(String verifyCode, HttpServletRequest request){
        String c = stringRedisTemplate.opsForValue().get("c");
        if (verifyCode.equals(c)){
            return "验证码校验通过";
        }else {
            return "验证码校验不通过";
        }
    }

    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request,HttpServletResponse response){

        try {
            /*//基础
            CaptchaUtil.out(request,response);*/
            //算术
            ArithmeticCaptcha arithmeticCaptcha = new ArithmeticCaptcha(100, 50);
            arithmeticCaptcha.setLen(3);//设置了加减乘除的个数
            //arithmeticCaptcha.text();//这个结果就是上述算法的结果
            CaptchaUtil.out(arithmeticCaptcha,request,response);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request){
        boolean ver = CaptchaUtil.ver(verifyCode, request);
        if (ver){
            HappyCaptcha.remove(request);
            return "验证码校验通过";
        }else {
            return "验证码校验不通过";
        }
    }
}
