package com.msb.md5;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

public class EncoderOrDecoderTest {

    @Test
    public void encoderAndDecode(){
        String sourceString ="123456";
        String s= DigestUtils.md5DigestAsHex(sourceString.getBytes());
        System.out.println("第一次加密的值为："+s);
        s= DigestUtils.md5DigestAsHex(sourceString.getBytes());
        System.out.println("第二次加密的值为："+s);
    }

    @Test
    public void bcrypt(){
        BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();
        String sourceString ="123456";
        String s = bCryptPasswordEncoder.encode(sourceString);

        System.out.println("第一次加密的值为："+s);
        boolean b = bCryptPasswordEncoder.matches(sourceString, s);
        System.out.println("第一次验证是否正确："+b);
        s = bCryptPasswordEncoder.encode(sourceString);
        System.out.println("第二次加密的值为："+s);
        b = bCryptPasswordEncoder.matches(sourceString, s);
        System.out.println("第二次验证是否正确："+b);
    }
}
