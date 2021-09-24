package com.msb.dongbao.protal.web.controller;

import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.msb.dongbao.protal.web.code.ImageCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/code")
public class VerifyCodeController {
    String attrName="verifyCode";
    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request,HttpServletResponse response){
        try {
            ImageCode imageCode=ImageCode.getInstance();
            //验证码的值
            String code = imageCode.getCode();
            request.getSession().setAttribute(attrName,code);
            //验证码的图片
            ByteArrayInputStream image = imageCode.getImage();
            //读取验证码图片
            response.setContentType("image/jpeg");
            byte[] bytes=new byte[1024];
            try(ServletOutputStream outputStream=response.getOutputStream()){
                    while (image.read(bytes)!=-1){
                        outputStream.write(bytes);
                    }
            }
        } catch (Exception e) {
            System.out.println("验证码生成异常");
        }


    }
    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request){
        String string = request.getSession().getAttribute(attrName).toString();
        if (verifyCode.equals(string)){
            return "验证码校验通过";
        }
        return "验证码校验不通过";
    }
}
