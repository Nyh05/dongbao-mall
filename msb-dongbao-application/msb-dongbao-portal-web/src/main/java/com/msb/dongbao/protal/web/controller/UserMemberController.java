package com.msb.dongbao.protal.web.controller;

import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.msb.dongbao.common.base.result.ResultWrapper;
import com.msb.dongbao.common.utill.JwtUtil;
import com.msb.dongbao.ums.entity.UmsMember;
import com.msb.dongbao.ums.entity.dto.UserMemberLoginParamDTO;
import com.msb.dongbao.ums.entity.dto.UserMemberRegisterParamDTO;
import com.msb.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.transform.Result;

@RestController
@RequestMapping("/user-member")
public class UserMemberController {

    @Autowired
    UmsMemberService umsMemberService;
    //第二种方法
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    /*@PostMapping("/register")
    public ResultWrapper register(@RequestBody @Valid UserMemberRegisterParamDTO userMemberRegisterParamDTO){
        *//*String rowPassword = userMemberRegisterParamDTO.getPassword();
        BCryptPasswordEncoder  bCryptPasswordEncoder =new BCryptPasswordEncoder();
        String newPassword = bCryptPasswordEncoder.encode(rowPassword);
        userMemberRegisterParamDTO.setPassword(newPassword);*//*
        *//*md5  :  md5(原始值)=m1
        彩虹表攻击。（md5值===原始值）对应表。（md5（原始值）每次生成的值都一样）
        使用BCryptPasswordEncoder进行加密，每次产生的加密密码都不一样，防止彩虹表攻击*//*

        //第二种方法引入passwordencoder，在applicaltion中注入bean
        //int i = 1/0;
        String newPassword = passwordEncoder.encode(userMemberRegisterParamDTO.getPassword());
        userMemberRegisterParamDTO.setPassword(newPassword);
        umsMemberService.register(userMemberRegisterParamDTO);
        //umsMemberService.register();
        return ResultWrapper.getSuccessBuilder().data(null).build();
    }*/
    @PostMapping("/register")
    public ResultWrapper register(@RequestBody @Valid UserMemberRegisterParamDTO userMemberRegisterParamDTO){
       return umsMemberService.register(userMemberRegisterParamDTO);
    }

    /*@PostMapping("/login")
    public String login(@RequestBody UserMemberLoginParamDTO userMemberLoginParamDTO){
        return umsMemberService.login(userMemberLoginParamDTO);
        //return "token";
    }*/
   /* @PostMapping("/login")
    public String login(@RequestBody UserMemberLoginParamDTO userMemberLoginParamDTO){
        return umsMemberService.login(userMemberLoginParamDTO);
    }*/
    @PostMapping("/login")
    public ResultWrapper login(@RequestBody UserMemberLoginParamDTO userMemberLoginParamDTO){
        return umsMemberService.login(userMemberLoginParamDTO);
    }



    //解析token进行登录验证

    /**
     * 这是测试的，系统中的任意一个接口
     * @param token
     * @return
     */
    @GetMapping("/test-verify")
    public String verify(String token){
        String s = JwtUtil.ParseToken(token);
        //token延期处理--直接在创建一个token返回即可
        String t = JwtUtil.CreatToken(s);
        return t;
    }

    @PostMapping("/edit")
    @TokenCheck
    public ResultWrapper edit(@RequestBody UmsMember umsMember){
        System.out.println("edit.............");
        return umsMemberService.edit(umsMember);
    }
}
