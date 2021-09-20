package com.msb.dongbao.protal.web.controller;

import com.msb.dongbao.ums.entity.dto.UserMemberRegisterParamDTO;
import com.msb.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/register")
    public String register(@RequestBody UserMemberRegisterParamDTO userMemberRegisterParamDTO){
        /*String rowPassword = userMemberRegisterParamDTO.getPassword();
        BCryptPasswordEncoder  bCryptPasswordEncoder =new BCryptPasswordEncoder();
        String newPassword = bCryptPasswordEncoder.encode(rowPassword);
        userMemberRegisterParamDTO.setPassword(newPassword);*/
        /*md5  :  md5(原始值)=m1
        彩虹表攻击。（md5值===原始值）对应表。（md5（原始值）每次生成的值都一样）
        使用BCryptPasswordEncoder进行加密，每次产生的加密密码都不一样，防止彩虹表攻击*/

        //第二种方法引入passwordencoder，在applicaltion中注入bean
        String newPassword = passwordEncoder.encode(userMemberRegisterParamDTO.getPassword());
        userMemberRegisterParamDTO.setPassword(newPassword);
        umsMemberService.register(userMemberRegisterParamDTO);
        //umsMemberService.register();
        return "register";
    }
}
