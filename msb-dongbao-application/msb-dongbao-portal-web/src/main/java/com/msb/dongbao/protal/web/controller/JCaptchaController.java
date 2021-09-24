package com.msb.dongbao.protal.web.controller;

import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.msb.dongbao.protal.web.code.ImageCode;
import com.msb.dongbao.protal.web.util.JCaptchaUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/jacaptcha")
public class JCaptchaController {
    String attrName="verifyCode";
    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request,HttpServletResponse response){
        try {
            String id = request.getSession().getId();

            BufferedImage bufferedImage = JCaptchaUtil.getImageCaptchaService().getImageChallengeForID(id);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(byteArrayOutputStream);

            jpegEncoder.encode(bufferedImage);

            response.setHeader("Cache-Control","no-store");
            response.setContentType("image/jpeg");
            byte[] bytes =byteArrayOutputStream.toByteArray();
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request){
        /*String string = request.getSession().getAttribute(attrName).toString();
        if (verifyCode.equals(string)){
            return "验证码校验通过";
        }*/
        Boolean aBoolean = JCaptchaUtil.getImageCaptchaService().validateResponseForID(request.getSession().getId(), verifyCode);
        if (aBoolean){
            return "验证码校验通过";
        }else {
            return "验证码校验不通过";
        }
    }
}
