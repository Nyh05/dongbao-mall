package com.msb.dongbao.common.utill;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtUtil {
    private static final String secret= "lsadf";
    public static String CreatToken(String subject){
        String token = Jwts.builder().setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis()+1000*30))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return token;
    }

    public static String ParseToken(String token){
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        String subject = body.getSubject();
        return subject;
    }
    //作为测试token创建解析使用
    public static void main(String[] args) throws InterruptedException {
        String ss="教育";
        String s = CreatToken(ss);
        System.out.println("获得的token"+s);
        String s1 = ParseToken(s);
        System.out.println("解析出来的值为："+s1);
        TimeUnit.SECONDS.sleep(4);
        s1 = ParseToken(s);
        System.out.println("解析出来的值为："+s1);

    }
}
