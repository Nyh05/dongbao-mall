package com.msb.dongbao.protal.web.controller;

import com.msb.dongbao.protal.web.util.SliderUtil;
import com.msb.dongbao.protal.web.util.VerificationVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/my-slider")
/*
* 相关的类以及jar包：hutool-all SliderUtil  VerificationVO   VerifyImageUtils
*
* */
public class MySliderController {

    @GetMapping("/generator")
    public VerificationVO generatorCode(HttpServletRequest request, HttpServletResponse response) {

        return SliderUtil.cut();
    }

    @GetMapping("/verify")
    public String verify(String verifyCode, HttpServletRequest request) {

//		Boolean aBoolean = kaptcha.validate(verifyCode, 10);
//		if (aBoolean) {
//			return "通过";
//		}
        return "不通过";
    }
}

