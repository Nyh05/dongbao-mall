package com.msb.dongbao.protal.web.controller;

import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.msb.dongbao.protal.web.util.JCaptchaUtil;
import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/happy-captcha")
public class HappyController {
    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request,HttpServletResponse response){

        HappyCaptcha.require(request,response)
                .style(CaptchaStyle.ANIM)//动态变化，但是图像中数字的内容不变，动图
                .type(CaptchaType.ARITHMETIC_ZH)
                .build().finish();


    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request){
        boolean verification = HappyCaptcha.verification(request, verifyCode, true);
        if (verification){
            HappyCaptcha.remove(request);
            return "验证码校验通过";
        }else {
            return "验证码校验不通过";
        }
    }
}
